package com.chhabinath.expense_tracker_api.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chhabinath.expense_tracker_api.entity.Expense;
import com.chhabinath.expense_tracker_api.repository.ExpenseRepository;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseRepository repository;

    @Override
    public Expense addExpense(Expense expense) {
        return repository.save(expense);
    }

    @Override
    public List<Expense> getAllExpenses() {
        return repository.findAll();
    }

    @Override
    public Expense getExpenseById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Expense updateExpense(Long id, Expense expense) {
        expense.setId(id);
        return repository.save(expense);
    }

    @Override
    public void deleteExpense(Long id) {
        repository.deleteById(id);
    }
    
    @Override
    public List<Expense> filterExpenses(String category, Date startDate, Date endDate) {
        return repository.filterExpenses(category, startDate, endDate);
    }
    
    @Override
    public Map<String, Double> getCategoryWiseSummary() {
        List<Object[]> data = repository.getCategoryWiseSummary();
        Map<String, Double> result = new HashMap<>();
        for (Object[] row : data) {
            result.put((String) row[0], (Double) row[1]);
        }
        return result;
    }

    @Override
    public Double getTotalExpense() {
        return repository.getTotalExpense();
    }

}
