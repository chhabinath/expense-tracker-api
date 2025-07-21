package com.chhabinath.expense_tracker_api.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.chhabinath.expense_tracker_api.entity.Expense;

public interface ExpenseService {

    Expense addExpense(Expense expense);

    List<Expense> getAllExpenses();

    Expense getExpenseById(Long id);

    Expense updateExpense(Long id, Expense expense);

    void deleteExpense(Long id);
    
    List<Expense> filterExpenses(String category, Date startDate, Date endDate);

    Map<String, Double> getCategoryWiseSummary();
    
    Double getTotalExpense();

}