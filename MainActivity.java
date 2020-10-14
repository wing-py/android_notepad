public class MainActivity extends AppCompatActivity {
    RecyclerView listview;
    ArrayList<String> adapterlist=new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview=findViewById(R.id.listview);
        listview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        listview.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        String[] templ= fileList();
        for(int i=0;i<templ.length;i++){adapterlist.add(templ[i]);}
        MyAdapter adapter=new MyAdapter(MainActivity.this,adapterlist);
        listview.setAdapter(adapter);
        Button btn=findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText text=findViewById(R.id.text);
                String temp=text.getText().toString();
                adapterlist.add(temp);
                listview.getAdapter().notifyDataSetChanged();
                try {
                    create(temp);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }


            }

            });
    }
    private void create(String temp) throws FileNotFoundException {
        FileOutputStream out=null;
        out=openFileOutput(temp,MODE_PRIVATE);
        try {
            String str="hello world";
            out.write(str.getBytes());
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





}
