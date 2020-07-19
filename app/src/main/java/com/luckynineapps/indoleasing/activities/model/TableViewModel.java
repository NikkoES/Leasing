package com.luckynineapps.indoleasing.activities.model;

import com.luckynineapps.indoleasing.models.pinjaman.Pinjaman;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hakim on 06/05/2019.
 */
public class TableViewModel {

    private List<String> headerData;
    private List<Pinjaman> cellData;

    public TableViewModel(List<String> headerData, List<Pinjaman> cellData) {
        this.headerData = headerData;
        this.cellData = cellData;
    }

    private List<RowHeader> getSimpleRowHeaderList() {
        List<RowHeader> list = new ArrayList<>();
        for (int i = 0; i < headerData.size(); i++) {
            RowHeader header = new RowHeader(headerData.get(i), headerData.get(i));
            list.add(header);
        }

        return list;
    }

    private List<List<Cell>> getCellListData() {
        List<List<Cell>> list = new ArrayList<>();
        List<Cell> nama = new ArrayList<>();
        List<Cell> umur = new ArrayList<>();
        List<Cell> jenis = new ArrayList<>();
        List<Cell> warga = new ArrayList<>();
        List<Cell> dokumen = new ArrayList<>();
        List<Cell> bank = new ArrayList<>();
        List<Cell> nominal = new ArrayList<>();
        List<Cell> penghasilan = new ArrayList<>();
        List<Cell> limit = new ArrayList<>();
        List<Cell> tenor = new ArrayList<>();
        List<Cell> bunga = new ArrayList<>();
        List<Cell> lambat = new ArrayList<>();
        List<Cell> dpbaru = new ArrayList<>();
        List<Cell> dpbekas = new ArrayList<>();
        for (int i = 0; i < cellData.size(); i++) {
            Pinjaman pinjaman = cellData.get(i);
            nama.add(new Cell(pinjaman.getNama()));
            umur.add(new Cell(pinjaman.getUmur()));
            StringBuilder stringJenis = new StringBuilder();
            for (int j = 0; j < pinjaman.getJenis().size(); j++) {
                if (j == 0) {
                    stringJenis.append(pinjaman.getJenis().get(j));
                } else {
                    stringJenis.append(" - ").append(pinjaman.getJenis().get(j));
                }
            }
            jenis.add(new Cell(stringJenis.toString()));
            warga.add(new Cell(pinjaman.getKewarganegaraan()));
            dokumen.add(new Cell(pinjaman.getDokumen()));
            bank.add(new Cell(pinjaman.getBank()));
            nominal.add(new Cell(pinjaman.getNominal()));
            penghasilan.add(new Cell(pinjaman.getPenghasilan()));
            limit.add(new Cell(pinjaman.getLimitPinjaman()));
            tenor.add(new Cell(pinjaman.getTenor()));
            bunga.add(new Cell(pinjaman.getBunga()));
            lambat.add(new Cell(pinjaman.getMaxLambat()));
            dpbaru.add(new Cell(pinjaman.getDpBaru()));
            dpbekas.add(new Cell(pinjaman.getDpBekas()));
        }
        list.add(nama);
        list.add(umur);
        list.add(jenis);
        list.add(warga);
        list.add(dokumen);
        list.add(bank);
        list.add(nominal);
        list.add(penghasilan);
        list.add(limit);
        list.add(tenor);
        list.add(bunga);
        list.add(lambat);
        list.add(dpbaru);
        list.add(dpbekas);

        return list;
    }

    public List<List<Cell>> getCellList() {
        return getCellListData();
    }

    public List<RowHeader> getRowHeaderList() {
        return getSimpleRowHeaderList();
    }
}
