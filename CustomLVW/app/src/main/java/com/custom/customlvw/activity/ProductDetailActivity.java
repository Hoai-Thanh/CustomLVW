package com.custom.customlvw.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.custom.customlvw.R;
import com.custom.customlvw.global.Global;

import java.io.IOException;
import java.net.URL;

public class ProductDetailActivity extends Activity {

    private ImageView imgProduct;
    private TextView productName;
    private TextView price;
    private TextView description;
    private Bitmap bmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lvw_product_detail_activity);

        initView();
        fillData();
    }

    private void initView() {
        imgProduct = (ImageView) findViewById(R.id.lvw_productDetailActivity_imgProduct);
        productName = (TextView) findViewById(R.id.lvw_productDetailActivity_productName);
        price = (TextView) findViewById(R.id.lvw_productDetailActivity_price);
        description = (TextView) findViewById(R.id.lvw_productDetailActivity_description);
    }
    private void fillData() {
        final String getImage = getIntent().getExtras().getString(Global.IMAGE);
        String getProductName = getIntent().getExtras().getString(Global.PRODUCT_NAME);
        String getPrice = getIntent().getExtras().getString(Global.PRICE);
        String getDescription = getIntent().getExtras().getString(Global.DESCRIPTION);

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    URL url = new URL(getImage);
                    bmp = BitmapFactory.decodeStream(url.openConnection()
                            .getInputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ProductDetailActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        imgProduct.setImageBitmap(bmp);
                    }
                });

            }
        });
        thread.start();

        productName.setText(getProductName);
        price.setText(getPrice);
        description.setText(getDescription);
    }
}
