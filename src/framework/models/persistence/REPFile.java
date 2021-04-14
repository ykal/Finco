package framework.models.persistence;

import java.util.ArrayList;
import java.util.List;

import framework.controllers.results.IResult;
import framework.models.account.Report;

public class REPFile {
	private List<Report> reports;

	public REPFile() {
		reports = new ArrayList<>();
	}

	public IResult addReport(Report report) {
		return null;
	}

	public List<Report> genReport() {
		return null;
	}
}
