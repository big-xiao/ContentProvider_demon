package demon.com.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.widget.SwitchCompat;

import demon.com.contentprovider.Helper.DBHelper;

public class Mycontentprovide extends ContentProvider {

	Context context=null;
	DBHelper mdbHelper =null;
	SQLiteDatabase db=null;
	private final static String AUTHORITY="demon.com.contentprovider";
	private final static int USER_CODE=1;
	private final static int JOB_CODE=2;
	private  static UriMatcher mMatcher;

	static {

		mMatcher=new UriMatcher(UriMatcher.NO_MATCH);
		mMatcher.addURI(AUTHORITY,"user",USER_CODE);
		mMatcher.addURI(AUTHORITY,"job",JOB_CODE);


	}
	@Override
	public boolean onCreate() {
		context=getContext();
		mdbHelper=new DBHelper(getContext());
		db=mdbHelper.getWritableDatabase();
		db.execSQL("delete from user");
		db.execSQL("insert into user values(1,'fengbujue');");
		db.execSQL("insert into user values(2,'wangtanzhi');");

		db.execSQL("delete from job");
		db.execSQL("insert into job values(1,'Android');");
		db.execSQL("insert into job values(2,'iOS');");


		return true;
	}
    public  String gettableName(Uri uri)
    {
    	String table=null;
    	mMatcher.match(uri);

    	switch (mMatcher.match(uri))
	    {
		    case USER_CODE:
		    	table=DBHelper.USER_TABLE_NAME;
					    break;
		    case JOB_CODE:
			    table=DBHelper.JOB_TABLE_NAME;
			    break;

	    }
	    return  table;
    }
	@Nullable
	@Override
	public Cursor query(@Nullable Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
   String table=gettableName(uri);
		return db.query(table,projection,selection,selectionArgs,null,null,sortOrder);


	}

	@Nullable
	@Override
	public String getType( Uri uri) {
		return null;
	}

	@Nullable
	@Override
	public Uri insert( Uri uri, @Nullable ContentValues values) {
		String table=gettableName(uri);
		db.insert(table,null,values);
		context.getContentResolver().notifyChange(uri, null);
		return uri;
	}

	@Override
	public int delete( Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
		return 0;
	}

	@Override
	public int update( Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
		return 0;
	}
}
