package de.amr.games.darts.game.ui;

import java.util.Collections;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import de.amr.games.darts.checkout.CheckOut;

public class CheckOutsTableModel extends AbstractTableModel {

	private List<CheckOut> results;

	public CheckOutsTableModel() {
		results = Collections.emptyList();
	}

	public void setResults(List<CheckOut> results) {
		this.results = results;
		fireTableDataChanged();
	}

	@Override
	public int getRowCount() {
		return results.size();
	}

	@Override
	public int getColumnCount() {
		return 1;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return results.get(rowIndex).toString();
	}

	@Override
	public String getColumnName(int column) {
		return "Check-Outs";
	}

}
