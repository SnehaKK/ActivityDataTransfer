package com.example.activitydatatransfer;

import android.provider.BaseColumns;

public final class DataContract {
	// To prevent someone from accidentally instantiating the contract class,
	// give it an empty constructor.
	public DataContract() {
	}

	/* Inner class that defines the table contents */
	public static abstract class DataEntry implements BaseColumns {
		public static final String TABLE_NAME = "creditCardInfo";
		//public static final String COLUMN_NAME_ENTRY_ID = "entryId";
		public static final String COLUMN_NAME_FIRST_NAME = "firstName";
		public static final String COLUMN_NAME_LAST_NAME = "lastName";
		public static final String COLUMN_NAME_ADDRESS = "address";
		public static final String COLUMN_NAME_CC_NUMBER = "creditCardNumber";
	}
}
