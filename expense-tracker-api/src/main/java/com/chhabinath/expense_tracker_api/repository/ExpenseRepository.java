package com.chhabinath.expense_tracker_api.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.chhabinath.expense_tracker_api.entity.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

	@Query("SELECT e FROM Expense e WHERE " + "(:category IS NULL OR e.category = :category) AND "
			+ "(:startDate IS NULL OR e.date >= :startDate) AND " + "(:endDate IS NULL OR e.date <= :endDate)")
	List<Expense> filterExpenses(@Param("category") String category, @Param("startDate") Date startDate,
			@Param("endDate") Date endDate);

	@Query("SELECT e.category, SUM(e.amount) FROM Expense e GROUP BY e.category")
	List<Object[]> getCategoryWiseSummary();

	@Query("SELECT SUM(e.amount) FROM Expense e")
	Double getTotalExpense();

}
