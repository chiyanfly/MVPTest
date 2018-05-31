package com.example.hao.mvptest;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import widget.RecyclerViewExtended;

public class Listactivity extends Activity implements ListContract.IView {


    private RecyclerViewExtended recyclerView_list;
    private Listviewadapter mylistadpter;
    private Button Button_add;
    private ListContract.IPresenter mypresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_test);
        recyclerView_list = (RecyclerViewExtended) findViewById(R.id.list_ref_points);
        Button_add = (Button) findViewById(R.id.button_add);

        Button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showaddDialog();
            }
        });

        mylistadpter = new Listviewadapter();
        recyclerView_list.setAdapter(mylistadpter);
        recyclerView_list.setLayoutManager(new LinearLayoutManager(this));

    }


    @Override
    protected void onStart() {
        super.onStart();
        if (mypresenter == null) {
            mypresenter = createMainPresenter();
            mypresenter.onAttachView(this);
        }

        mypresenter.onrefreshlist();
    }

    private void showModifyDialog(int adapterPosition) {
    }


    private void showaddDialog() {

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        final EditText edittext = new EditText(getApplicationContext());
        alert.setMessage("Enter Your text");
        alert.setTitle("Add text");

        alert.setView(edittext);

        alert.setPositiveButton("Yes Option", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //What ever you want to do with the value

                String newaddtext = edittext.getText().toString();
                mypresenter.onAddtext(newaddtext);
            }
        });

        alert.setNegativeButton("No Option", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // what ever you want to do with No option.
            }
        });

        alert.show();



    }


    private void showDeleteDialog(int adapterPosition) {
    }



    @Override
    public void settextlist(ArrayList<String> textlist) {
        mylistadpter.setTextlist(textlist);
    }

    @Override
    public void notifytextAdded() {
        mylistadpter.notifyItemInserted(mylistadpter.textlist.size()-1);
    }

    @Override
    public void notifytextModified(int position) {

    }

    @Override
    public void notifytextRemoved(int position) {

    }

    @Override
    public ListContract.IPresenter createMainPresenter() {
        return new Listpresenter();
    }


    private class ListviewHolder extends RecyclerView.ViewHolder {

        public View itemview;
        public TextView textView;


        public ListviewHolder(View item, TextView t) {
            super(item);
            itemview = item;
            textView = t;

            item.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    showOptiondialog();
                    return true;
                }
            });


        }

        private void showOptiondialog() {
            // show dialog for modifying and deleting
            String[] options = new String[]{"modifier", "supprimer"};
            AlertDialog.Builder builder = new AlertDialog.Builder(Listactivity.this);
            builder.setItems(options, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (which == 0) {
                        // modify
                        showModifyDialog(getAdapterPosition());
                    } else if (which == 1) {
                        // delete
                        showDeleteDialog(getAdapterPosition());
                    }
                }
            });
            builder.create().show();


        }


    }

    private class Listviewadapter extends RecyclerView.Adapter<ListviewHolder> {

        private ArrayList<String> textlist;

        public void setTextlist(ArrayList<String> textlist) {
            this.textlist = textlist;
        }

        public ArrayList<String> getTextlist() {
            return textlist;
        }

        @Override
        public ListviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(Listactivity.this).inflate(R.layout.list_item, parent, false);
            TextView textView = (TextView) v.findViewById(R.id.text_item);
            return new ListviewHolder(v, textView);

        }

        @Override
        public void onBindViewHolder(ListviewHolder holder, int position) {
            holder.textView.setText(textlist.get(position));
        }

        @Override
        public int getItemCount() {
            {
                return (textlist != null) ? textlist.size() : 0;
            }
        }
    }

}




