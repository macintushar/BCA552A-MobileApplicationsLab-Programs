package com.tusharselvakumar.kiacarapp;

import android.os.Bundle;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Quiz extends AppCompatActivity {

    private TextView questionTextView;
    private ListView listView;
    private List<String> selectedItems;
    private ArrayAdapter<String> adapter;
    private ActionMode actionMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        setSupportActionBar(findViewById(R.id.toolbar));

        questionTextView = findViewById(R.id.questionTextView);

        registerForContextMenu(questionTextView);

        listView = findViewById(R.id.listView);
        selectedItems = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, getData());
        listView.setAdapter(adapter);

        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                if (checked) {
                    selectedItems.add(adapter.getItem(position));
                } else {
                    selectedItems.remove(adapter.getItem(position));
                }
                mode.setTitle(selectedItems.size() + " selected");
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                getMenuInflater().inflate(R.menu.contextual_action_bar_menu, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                int itemID = item.getItemId();

                if (itemID == R.id.action_delete) {
                    deleteSelectedItems();
                    mode.finish();
                    return true;
                }

                if (itemID == R.id.action_check) {
                    for (String opt : selectedItems) {
                        if(opt == "Collection of classes and interfaces") {
                            Toast.makeText(getApplicationContext(),"Correct Answer", Toast.LENGTH_SHORT).show();

                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Wrong Answer", Toast.LENGTH_SHORT).show();
                        }
                        return true;
                    }
                }

                else {
                    return false;
                }
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                selectedItems.clear();
            }
        });
    }

    private List<String> getData() {
        List<String> data = new ArrayList<>();
        data.add("Collection of editing tools");
        data.add("Collection of classes");
        data.add("Collection of classes and interfaces");
        data.add("Collection of interfaces");
        return data;
    }

    private void deleteSelectedItems() {
        for (String item : selectedItems) {
            adapter.remove(item);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.menu_item1) {
            // Handle menu item 1 click
            Toast.makeText(this, "Wrong Answer", Toast.LENGTH_SHORT).show();
            return true;
        } else if (itemId == R.id.menu_item2) {
            // Handle menu item 2 click
            Toast.makeText(this, "Correct Answer", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.context_menu_item2) {
            // Handle context menu item 2 click
            Toast.makeText(this, "Correct Answer", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(this, "Wrong Answer", Toast.LENGTH_SHORT).show();
            return super.onContextItemSelected(item);
        }
    }

    public void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.inflate(R.menu.popup_menu);

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.popup_menu_item2) {
                    Toast.makeText(Quiz.this, "Correct Answer", Toast.LENGTH_SHORT).show();
                    return true;
                } else {
                    Toast.makeText(Quiz.this, "Wrong Answer", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
        });

        popupMenu.show();
    }
}