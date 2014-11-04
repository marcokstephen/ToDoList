package sm.com.todolist;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ToDoList extends Activity {

    SharedPreferences prefs;
    Context ctxt;

    public static final String SAVED_ITEMS = "com.sm.todolist.SAVED_ITEMS";
    final List<String> products = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);
        ctxt = this;
        prefs = PreferenceManager.getDefaultSharedPreferences(this);

        ListView todoListView = (ListView) findViewById(R.id.listView);
        Button addButton = (Button) findViewById(R.id.add_new_button);

        String item = prefs.getString(SAVED_ITEMS, "[{'key':'value'}]");
        JSONArray jsonArray = new JSONArray();
        try {
            jsonArray = new JSONArray(item);
            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String itemString = jsonObject.getString("key");
                products.add(itemString);
            }
        } catch (JSONException e){
            e.printStackTrace();
        }


        final ListViewAdapter lva = new ListViewAdapter(this, products);
        todoListView.setAdapter(lva);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText et = new EditText(ctxt);
                new AlertDialog.Builder(ctxt)
                        .setTitle("Add new thing!")
                        .setView(et)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String itemToAdd = et.getText().toString();
                                products.add(itemToAdd);

                                String itemsInSharedPrefs = prefs.getString(SAVED_ITEMS, "[{'key':'value'}]");
                                JSONArray ja = new JSONArray();
                                try {
                                    ja = new JSONArray(itemsInSharedPrefs);
                                    JSONObject newItem = new JSONObject();
                                    newItem.put("key", itemToAdd);
                                    ja.put(newItem);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                SharedPreferences.Editor editor = prefs.edit();
                                editor.putString(SAVED_ITEMS, ja.toString());
                                editor.commit();

                                lva.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //do nothing
                            }
                        })
                        .show();
            }
        });
    }
}
