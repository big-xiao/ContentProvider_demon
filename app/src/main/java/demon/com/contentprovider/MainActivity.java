package demon.com.contentprovider;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Uri uri=Uri.parse("content://demon.com.contentprovider/user");
		ContentValues values=new ContentValues();

		values.put("name","chenyixiao");

		ContentResolver resolver=getContentResolver();
		Cursor cursor= resolver.query(uri,new String []{"_id","name"},null,null,null);
		while (cursor.moveToNext())
		{
			System.out.println("query book:" + cursor.getInt(0) +" "+ cursor.getString(1));
		}

		resolver.insert(uri,values);
		cursor= resolver.query(uri,new String []{"_id","name"},null,null,null);
		while (cursor.moveToNext())
		{
			System.out.println("query book:" + cursor.getInt(0) +" "+ cursor.getString(1));
		}
		cursor.close();

		Uri uri2=Uri.parse("content://demon.com.contentprovider/job");
	//	ContentResolver resolver2=getContentResolver();

		ContentValues values2=new ContentValues();

		values2.put("job","paobing");

		Cursor cursor2 = resolver.query(uri2, new String[]{"_id","job"}, null, null, null);
		while (cursor2.moveToNext()){
			System.out.println("query job:" + cursor2.getInt(0) +" "+ cursor2.getString(1));

		}

		resolver.insert(uri2,values2);
		cursor2= resolver.query(uri2,new String []{"_id","job"},null,null,null);
		while (cursor2.moveToNext()){
			System.out.println("query job:" + cursor2.getInt(0) +" "+ cursor2.getString(1));

		}
		cursor.close();
	}

}
