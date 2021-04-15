package framework.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import framework.models.account.Account;
import framework.models.account.Report;

public class REPFile {
	private List<Report> reports;

	public REPFile() {
		reports = new ArrayList<>();
	}

	public void addReport(Report report) {
		reports.add(report);
	}

	public List<Report> filter(Predicate<Report> predicate) {
		return this.reports.stream().filter(predicate).collect(Collectors.toList());
	}

	public List<Report> genReport() {
		return null;
	}

	@Override
	public String toString() {
		return reports.stream()
				.map(report -> report.toString())
				.reduce((x, y) -> x + y)
				.orElse("");
	}
}
