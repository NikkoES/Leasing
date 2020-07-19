package com.luckynineapps.indoleasing.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.evrencoskun.tableview.adapter.AbstractTableAdapter;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;
import com.luckynineapps.indoleasing.R;
import com.luckynineapps.indoleasing.activities.model.Cell;
import com.luckynineapps.indoleasing.activities.model.ColumnHeader;
import com.luckynineapps.indoleasing.activities.model.RowHeader;
import com.luckynineapps.indoleasing.activities.model.TableViewModel;

/**
 * Created by Hakim on 06/05/2019.
 */
public class PerbandinganAdapter extends AbstractTableAdapter<ColumnHeader, RowHeader, Cell> {

    private TableViewModel mTableViewModel;
    private final LayoutInflater mInflater;

    public PerbandinganAdapter(Context context, TableViewModel mTableViewModel) {
        super(context);
        this.mTableViewModel = mTableViewModel;
        this.mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getColumnHeaderItemViewType(int position) {
        return 0;
    }

    @Override
    public int getRowHeaderItemViewType(int position) {
        return 0;
    }

    @Override
    public int getCellItemViewType(int position) {
        return 0;
    }

    @Override
    public AbstractViewHolder onCreateCellViewHolder(ViewGroup parent, int viewType) {

        // Create a Cell ViewHolder
        return new CellViewHolder(mInflater.inflate(R.layout.table_view_cell_layout, parent, false));
    }

    @Override
    public void onBindCellViewHolder(AbstractViewHolder holder, Object cellItemModel, int columnPosition, int rowPosition) {
        Cell cell = (Cell) cellItemModel;
        CellViewHolder viewHolder = (CellViewHolder) holder;

        viewHolder.setCell(cell);
    }

    @Override
    public AbstractViewHolder onCreateColumnHeaderViewHolder(ViewGroup parent, int viewType) {
        // TODO: check
        //Log.e(LOG_TAG, " onCreateColumnHeaderViewHolder has been called");
        // Get Column Header xml Layout
        View layout = mInflater.inflate(R.layout.table_view_column_header_layout, parent, false);

        // Create a ColumnHeader ViewHolder
        return new ColumnHeaderViewHolder(layout, getTableView());    }

    @Override
    public void onBindColumnHeaderViewHolder(AbstractViewHolder holder, Object columnHeaderItemModel, int columnPosition) {
        ColumnHeader columnHeader = (ColumnHeader) columnHeaderItemModel;

        // Get the holder to update cell item text
        ColumnHeaderViewHolder columnHeaderViewHolder = (ColumnHeaderViewHolder) holder;
        columnHeaderViewHolder.setColumnHeader(columnHeader);
    }

    @Override
    public AbstractViewHolder onCreateRowHeaderViewHolder(ViewGroup parent, int viewType) {
        View layout = mInflater.inflate(R.layout.table_view_row_header_layout, parent, false);

        // Create a Row Header ViewHolder
        return new RowHeaderViewHolder(layout);
    }

    @Override
    public void onBindRowHeaderViewHolder(AbstractViewHolder holder, Object rowHeaderItemModel, int rowPosition) {
        RowHeader rowHeader = (RowHeader) rowHeaderItemModel;



        // Get the holder to update row header item text
        RowHeaderViewHolder rowHeaderViewHolder = (RowHeaderViewHolder) holder;
        rowHeaderViewHolder.row_header_textview.setText(String.valueOf(rowHeader.getData()));
    }

    @Override
    public View onCreateCornerView() {
        View corner = mInflater.inflate(R.layout.table_view_corner_layout, null);
        return corner;
    }
}
