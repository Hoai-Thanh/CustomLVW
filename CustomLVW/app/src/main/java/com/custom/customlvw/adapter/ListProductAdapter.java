package com.custom.customlvw.adapter;

import java.io.InputStream;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.custom.customlvw.R;
import com.custom.customlvw.model.Product;

public class ListProductAdapter extends BaseAdapter {

	private List<Product> items;
	private Context context;

	public ListProductAdapter(Context context, List<Product> items) {
		this.items = items;
		this.context = context;
	}

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup viewGroup) {
		ViewHolder viewHolder;
		if (view == null) {
			LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			view = layoutInflater.inflate(R.layout.lvw_item_product_activity, viewGroup, false);
			
			viewHolder = new ViewHolder();
			viewHolder.imgProduct = (ImageView) view.findViewById(R.id.lvw_listProductActivity_imgProduct);
			viewHolder.productName = (TextView) view.findViewById(R.id.lvw_listProductActivity_productName);
            viewHolder.price = (TextView) view.findViewById(R.id.lvw_listProductActivity_price);
			
			view.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}
		
		new DownloadImageTask(viewHolder.imgProduct).execute(items.get(position).getImage());
		viewHolder.productName.setText(items.get(position).getProductName().toString());
        viewHolder.price.setText(items.get(position).getPrice().toString());
		
		return view;
	}

	class ViewHolder {
		public ImageView imgProduct;
		public TextView productName;
        public TextView price;
	}

	private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
		ImageView bmImage;

		public DownloadImageTask(ImageView bmImage) {
			this.bmImage = bmImage;
		}

		protected Bitmap doInBackground(String... urls) {
			String urldisplay = urls[0];
			Bitmap mIcon11 = null;
			try {
				InputStream in = new java.net.URL(urldisplay).openStream();
				mIcon11 = BitmapFactory.decodeStream(in);
			} catch (Exception e) {
				Log.e("Error", e.getMessage());
				e.printStackTrace();
			}
			return mIcon11;
		}

		protected void onPostExecute(Bitmap result) {
			bmImage.setImageBitmap(result);
		}

	}

}
