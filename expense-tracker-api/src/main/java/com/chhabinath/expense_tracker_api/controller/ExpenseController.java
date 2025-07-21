package com.chhabinath.expense_tracker_api.controller;

import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chhabinath.expense_tracker_api.entity.Expense;
import com.chhabinath.expense_tracker_api.service.ExpenseService;
import com.chhabinath.expense_tracker_api.util.PdfGenerator;

import java.io.IOException;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

	@Autowired
	private ExpenseService service;

	@PostMapping
	public Expense addExpense(@RequestBody Expense expense) {
		return service.addExpense(expense);
	}

	@GetMapping
	public List<Expense> getAllExpenses() {
		return service.getAllExpenses();
	}

	@GetMapping("/{id}")
	public Expense getExpenseById(@PathVariable Long id) {
		return service.getExpenseById(id);
	}

	@PutMapping("/{id}")
	public Expense updateExpense(@PathVariable Long id, @RequestBody Expense expense) {
		return service.updateExpense(id, expense);
	}

	@DeleteMapping("/{id}")
	public void deleteExpense(@PathVariable Long id) {
		service.deleteExpense(id);
	}

	@GetMapping("/filter")
	public List<Expense> filterExpenses(@RequestParam(required = false) String category,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
		return service.filterExpenses(category, startDate, endDate);
	}

	@GetMapping("/summary")
	public Map<String, Object> getSummary() {
		Map<String, Object> summary = new HashMap<>();
		summary.put("totalExpense", service.getTotalExpense());
		summary.put("categoryWiseSummary", service.getCategoryWiseSummary());
		return summary;
	}

	@GetMapping("/export/csv")
	public void exportToCSV(HttpServletResponse response) throws IOException {
		response.setContentType("text/csv");
		response.setHeader("Content-Disposition", "attachment; filename=expenses.csv");

		List<Expense> expenses = service.getAllExpenses();
		PrintWriter writer = response.getWriter();

		writer.println("ID,Title,Category,Amount,Date,Description");

		for (Expense expense : expenses) {
			writer.printf("%d,%s,%s,%.2f,%s,%s\n", expense.getId(), expense.getTitle(), expense.getCategory(),
					expense.getAmount(), expense.getDate(), expense.getDescription());
		}

		writer.flush();
		writer.close();
	}

	@GetMapping("/export/pdf")
	public void exportToPDF(HttpServletResponse response) throws Exception {
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename=expenses.pdf");

		List<Expense> expenses = service.getAllExpenses();
		PdfGenerator.generateExpenseReport(response.getOutputStream(), expenses);
	}

}