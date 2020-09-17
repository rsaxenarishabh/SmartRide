package com.smartrider24.Activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.smartrider24.R;
import com.smartrider24.model.accountdetailstatement.AccountStatement;
import com.smartrider24.model.customertracking.TrackBookingHistory;

import java.util.List;

public class AcountDetailAdapter extends RecyclerView.Adapter<AcountDetailAdapter.MyViewHolder> {

    private List<AccountStatement> accountStatements;
    public AcountDetailAdapter(List<AccountStatement> accountStatements) {
        this.accountStatements = accountStatements;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView bankStatementDate, bankStatementDebit, bankStatementCredit;

        public MyViewHolder(View view) {
            super(view);
            bankStatementDate = view.findViewById(R.id.txtDate);
            bankStatementDebit = view.findViewById(R.id.txtDebit);
            bankStatementCredit = view.findViewById(R.id.txtCredit);
        }
    }

    @Override
    public AcountDetailAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bank_statement_layout, parent, false);
        return new AcountDetailAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AcountDetailAdapter.MyViewHolder holder, int position) {
        AccountStatement accountStatement = accountStatements.get(position);
        if (accountStatement.getTransCredit() != null) {
            holder.bankStatementCredit.setText("\u20B9 " + accountStatement.getTransCredit());
        }
        if (accountStatement.getTransDebit() != null) {
            holder.bankStatementDebit.setText("\u20B9 " + accountStatement.getTransDebit());
        }
        if (accountStatement.getTransDate() != null) {
            holder.bankStatementDate.setText("" + accountStatement.getTransDate());
        }


    }

    @Override
    public int getItemCount() {
        if (accountStatements != null && accountStatements.size() > 0)
            return accountStatements.size();
        return 0;
    }


}


