package com.example.testactionbar.view;


/*public class MainActivity extends ActionBarActivity
 {

 // ActionBar mActionBar;
 // private DrawerLayout mDrawerLayout = null;

 @Override
 protected void onCreate(Bundle savedInstanceState)
 {
 super.onCreate(savedInstanceState);
 setContentView(R.layout.activity_main);
 // mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

 Button button = (Button) findViewById(R.id.btn);
 button.setOnClickListener(new OnClickListener()
 {

 @Override
 public void onClick(View v)
 {
 // 按钮按下，将抽屉打开
 // mDrawerLayout.openDrawer(Gravity.LEFT);

 }
 });

 initView();
 }

 @SuppressLint("NewApi")
 private void initView()
 {
 setOverflowShowingAlways();
 // mActionBar = getSupportActionBar();
 // mActionBar.setTitle("Test");
 }

 *//**
 * 将显示在底部的Menu显示在ActionBar
 */
/*
 * private void setOverflowShowingAlways() { try { ViewConfiguration config =
 * ViewConfiguration.get(this); Field menuKeyField = ViewConfiguration.class
 * .getDeclaredField("sHasPermanentMenuKey"); menuKeyField.setAccessible(true);
 * menuKeyField.setBoolean(config, false); } catch (Exception e) {
 * e.printStackTrace(); } }
 * 
 * @Override public boolean onCreateOptionsMenu(Menu menu) { MenuInflater
 * inflater = getMenuInflater(); inflater.inflate(R.menu.main, menu); //
 * MenuItem menuItem = menu.findItem(R.id.action_search); //
 * MenuItemCompat.setShowAsAction(menuItem, //
 * MenuItemCompat.SHOW_AS_ACTION_ALWAYS); return true; } }
 */