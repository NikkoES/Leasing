package com.luckynineapps.indoleasing.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.evrencoskun.tableview.TableView;
import com.evrencoskun.tableview.listener.ITableViewListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.luckynineapps.indoleasing.R;
import com.luckynineapps.indoleasing.activities.model.Cell;
import com.luckynineapps.indoleasing.activities.model.ColumnHeader;
import com.luckynineapps.indoleasing.activities.model.TableViewModel;
import com.luckynineapps.indoleasing.adapters.PerbandinganAdapter;
import com.luckynineapps.indoleasing.models.pinjaman.Pinjaman;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PerbandinganActivity extends AppCompatActivity implements ITableViewListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.layout_perbandingan)
    LinearLayout layoutPerbandingan;
    @BindView(R.id.ad_bottom)
    AdView bottomAds;
    @BindView(R.id.content_container)
    TableView tableView;
    @BindView(R.id.perbandingan)
    LinearLayout tableContainer;
    @BindView(R.id.title)
    LinearLayout titleContainer;

    private PerbandinganAdapter mTableViewAdapter;

    List<Pinjaman> listOld = new ArrayList<>();
    List<Pinjaman> list = new ArrayList<>();

    int dp0, dp1, dp2, dp4, dp8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perbandingan);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        list = (List<Pinjaman>) getIntent().getSerializableExtra("list_pinjaman");

        initAd();
        initSize();
        initTable();
    }

    private void initTable() {
        List<String> header = new ArrayList<>();
        header.add("Nama Pinjaman");
        header.add("Umur Peminjam");
        header.add("Jenis");
        header.add("Warga Negara");
        header.add("Dokumen");
        header.add("Bank");
        header.add("Nominal Pinjaman");
        header.add("Penghasilan Minimum");
        header.add("Limit Pinjaman");
        header.add("Tenor Pinjaman");
        header.add("Bunga");
        header.add("Max Keterlambatan");
        header.add("DP Baru");
        header.add("DP Bekas");
        TableViewModel tableViewModel = new TableViewModel(header, list);
        mTableViewAdapter = new PerbandinganAdapter(this, tableViewModel);
        tableView.setAdapter(mTableViewAdapter);
        tableView.setTableViewListener(this);
        mTableViewAdapter.setAllItems(new ArrayList<ColumnHeader>(), tableViewModel
                .getRowHeaderList(), tableViewModel.getCellList());
    }

    private void initAd() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        bottomAds.loadAd(adRequest);
    }

    private void initSize() {
        dp0 = getResources().getDimensionPixelSize(R.dimen.dp_0);
        dp1 = getResources().getDimensionPixelSize(R.dimen.dp_1);
        dp2 = getResources().getDimensionPixelSize(R.dimen.dp_2);
        dp4 = getResources().getDimensionPixelSize(R.dimen.dp_4);
        dp8 = getResources().getDimensionPixelSize(R.dimen.dp_8);
    }

    @OnClick({R.id.btn_back, R.id.btn_share, R.id.btn_pinjaman})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_share:
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBodyText = "Mau mendapatkan dana tunai dengan cepat.\nSilahkan download aplikasi ini, sekarang juga :\nhttp://play.google.com/store/apps/details?id=" + getApplicationContext().getPackageName();

                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Indo Leasing");
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBodyText);
                startActivity(Intent.createChooser(sharingIntent, "Indo Leasing"));
                break;
            case R.id.btn_pinjaman:
                finish();
                break;
        }
    }

    @Override
    public void onCellClicked(@NonNull RecyclerView.ViewHolder cellView, int column, int row) {
        Cell item = mTableViewAdapter.getCellItem(column, row);
        if (item.getId().length() > 70) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle(null);
            alertDialogBuilder.setMessage(item.getId());
            alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            alertDialogBuilder.create().show();
        }
    }

    @Override
    public void onCellLongPressed(@NonNull RecyclerView.ViewHolder cellView, int column, int row) {

    }

    @Override
    public void onColumnHeaderClicked(@NonNull RecyclerView.ViewHolder columnHeaderView, int column) {

    }

    @Override
    public void onColumnHeaderLongPressed(@NonNull RecyclerView.ViewHolder columnHeaderView, int column) {

    }

    @Override
    public void onRowHeaderClicked(@NonNull RecyclerView.ViewHolder rowHeaderView, int row) {

    }

    @Override
    public void onRowHeaderLongPressed(@NonNull RecyclerView.ViewHolder rowHeaderView, int row) {

    }
}
