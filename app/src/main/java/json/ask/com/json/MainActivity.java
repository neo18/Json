package json.ask.com.json;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {


    private Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Parsing Data
        Type type = new TypeToken<Data>() {
        }.getType();
        data = new Gson().fromJson(loadJSONFromAsset(), type);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        //Initialization list and setting adapter
        ListView listView = (ListView) findViewById(R.id.list);
        Adapter mAdapter = new Adapter();
        listView.setAdapter(mAdapter);

    }

    // Reading json form assets
    private String loadJSONFromAsset() {
        String json;
        try {
            InputStream is = getAssets().open("data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            //noinspection ResultOfMethodCallIgnored
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    // Holing view objects in a view holder
    static class ViewHolder {

        TextView items;
        TextView price;

    }

    // Inner Adapter class for showing list
    public class Adapter extends BaseAdapter {

        private LayoutInflater inflater;

        public Adapter() {
            this.inflater = LayoutInflater.from(MainActivity.this);
        }

        @Override
        public int getCount() {
            return data.getResultSet().getItems().size();
        }

        @Override
        public Object getItem(int position) {
            return data.getResultSet().getItems().get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            final ViewHolder holder;
            View view = convertView;
            if (view == null) {
                holder = new ViewHolder();
                view = inflater.inflate(R.layout.view_items, parent, false);
                holder.items = (TextView) view.findViewById(R.id.tvItem);
                holder.price = (TextView) view.findViewById(R.id.tvPrice);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }
            // providing value to view
            Data.Item val = data.getResultSet().getItems().get(position);
            holder.items.setText(val.getTitle());
            DecimalFormat df = new DecimalFormat("###.###");
            holder.price.setText(df.format(val.getPrice()));

            return view;
        }

    }
}
