package framework.controllers.commands;

import framework.controllers.results.DoneResult;
import framework.controllers.results.IResult;
import framework.models.account.Account;
import framework.models.account.IEntry;
import framework.persistence.ACCFile;
import framework.persistence.REPFile;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Report extends AbstractAction {

	REPFile repFile;
	ACCFile accFile;

	public Report(REPFile repFile, ACCFile accFile) {
		this.repFile = repFile;
		this.accFile = accFile;
	}

	@Override
	public IResult execute() {
//		TODO Do Big Refactoring :(.
		String report = "";
		for (Account account : this.accFile.accounts) {
			// get Reports of this month
			List<framework.models.account.Report> reports = this.repFile.filter(report1 ->
					report1.getAccId().equals(account.getId()) &&
							report1.getDate().getMonthValue() == LocalDate.now().getMonthValue() &&
							report1.getDate().getYear() == LocalDate.now().getYear()

			);
			// get previous month balance
			LocalDate now = LocalDate.now();
			LocalDate prevLocalDate = now.getMonthValue() == 1 ? LocalDate.of(now.getYear() -1,12, 1) :
					LocalDate.of(now.getYear(), now.getMonthValue(), 1);

			List<framework.models.account.Report> prevReports = reports.stream().filter(report1 -> report1.getDate().getYear() == prevLocalDate.getYear() && report1.getDate().getMonthValue() == prevLocalDate.getMonthValue())
					.sorted(Comparator.comparing(framework.models.account.Report::getDate, Comparator.reverseOrder())).collect(Collectors.toList());
			double prevBalance = prevReports.get(0) != null ? prevReports.get(0).getNewBalance() : 0;

			this.repFile.filter(report1 -> report1.getDate().getMonthValue() == LocalDate.now().getYear());
			// calculate total deposite
			Optional<Double> totalCredit = reports.stream().filter(report1 -> report1.getEntryType().equals(IEntry.DEPOSIT))
					.map(report1 -> report1.getAmount())
					.reduce(Double::sum);
			// calculate total withdraw
			Optional<Double> totalWithdraw = reports.stream().filter(report1 -> report1.getEntryType().equals(IEntry.WITHDRAW))
					.map(report1 -> report1.getAmount())
					.reduce(Double::sum);
			// calculate new balance
			double newBalance = prevBalance - totalCredit.orElse(0D) - totalWithdraw.orElse(0D) + account.getMp() * (prevBalance - totalCredit.orElse(0D)); // 0 - 100 + 900 + (-100)
			// calculate due payment
			double totalDue = account.getMp() * newBalance;
			// add to string
			report += getBillReport(account, prevBalance, totalCredit.orElse(0.0), totalWithdraw.orElse(0.0), newBalance, totalDue);
		}
		System.out.println(repFile);
		System.out.println("Reported");
		return new DoneResult(report);
	}

	String getBillReport(Account acc, double prevBalance,double totalCredit, double totalCharge, double newBalance, double due ) {
		String billstring = "";
		billstring = "Name= " + acc.getOwner().getName() + "\r\n";
		billstring += "Address= " + acc.getOwner().getStreet() + " " + acc.getOwner().getCity() + ", " + acc.getOwner().getState() + ", " + acc.getOwner().getZip() + "\r\n";
		billstring += "CC number=" + acc.getId() + "\r\n";
		billstring += "CC type= " +acc.getAccountType() +"\r\n";
		billstring += "Previous balance = $ " + prevBalance + "\r\n";
		billstring += "Total Credits = $ " + totalCredit +"\r\n";
		billstring += "Total Charges = $ " + totalCharge +"\r\n";
		billstring += "New balance = $ " + newBalance +"\r\n";
		billstring += "Total amount due = $ "+ due +"\r\n";
		billstring += "\r\n";
		return billstring;
	}
}
