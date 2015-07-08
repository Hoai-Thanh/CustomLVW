package com.custom.customlvw.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.custom.customlvw.R;
import com.custom.customlvw.adapter.ListProductAdapter;
import com.custom.customlvw.global.Global;
import com.custom.customlvw.model.Product;
import com.custom.customlvw.utils.ReadFileUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListProductActivity extends Activity {
    private static final String TAG = ListProductActivity.class.getSimpleName();

    private ListView lvwProduct;
    private List<Product> products;
    private ReadFileUtils readFile;
    private ProgressDialog progressDialog;

    private OnItemClickListener onItemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(ListProductActivity.this, ProductDetailActivity.class);
            intent.putExtra(Global.IMAGE, products.get(position).getImage());
            intent.putExtra(Global.PRODUCT_NAME, products.get(position).getProductName());
            intent.putExtra(Global.PRICE, products.get(position).getPrice());
            intent.putExtra(Global.DESCRIPTION, products.get(position).getDescription());
            intent.putExtra(Global.LONGITUDE, products.get(position).getLongitude());
            intent.putExtra(Global.LATITUDE, products.get(position).getLatitude());
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lvw_list_product_activity);
        products = new ArrayList<Product>();

        initView();
        initEvent();
        fillData();
    }

    private void initView() {
        lvwProduct = (ListView) findViewById(R.id.lvw_listProductActivity_lvwProduct);
    }

    private void initEvent() {
        lvwProduct.setOnItemClickListener(onItemClickListener);
    }

    private void fillData() {
        LoadProductTask execute = new LoadProductTask();
        execute.execute();
    }

    private class LoadProductTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(ListProductActivity.this);
            progressDialog.setMessage("Loading...");
            progressDialog.setIndeterminate(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            readFile = new ReadFileUtils();
            String jsonStr = readFile.readFile(ListProductActivity.this, R.raw.product);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    JSONArray arr = jsonObj.getJSONArray(Global.CONTACT);
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject c = arr.getJSONObject(i);
                        products.add(new Product(c.getString(Global.IMAGE), c.getString(Global.PRODUCT_NAME),
                                c.getString(Global.PRICE), c.getString(Global.DESCRIPTION),
                                c.getString(Global.LONGITUDE), c.getString(Global.LATITUDE)));
                    }
                } catch (JSONException e) {
                    Log.e(TAG, e.toString());
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            ListProductAdapter adapter = new ListProductAdapter(ListProductActivity.this, products);
            lvwProduct.setAdapter(adapter);
            progressDialog.dismiss();
        }

    }
}
