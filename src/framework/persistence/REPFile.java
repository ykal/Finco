package framework.persistence;

import java.util.ArrayList;
import java.util.List;

import framework.models.account.Report;

public class REPFile {
	private List<Report> reports;

	public REPFile() {
		reports = new ArrayList<>();
	}

	public void addReport(Report report) {
		reports.add(report);
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
