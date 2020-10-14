public class Note extends AppCompatActivity {
    String filetext;
    String filename;
    EditText note;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        Intent intent=this.getIntent();
        Bundle bundle=intent.getExtras();
        assert bundle != null;
        filename=bundle.getString("filename");
        TextView filenametext=findViewById(R.id.filenametext);
        filenametext.setText(filename);

        note=findViewById(R.id.note);
        filetext=read();
        note.setText(filetext);
        Button btn=findViewById(R.id.btn_save);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    save(note.getText().toString());

            }
        });
    }
    public String read(){
        FileInputStream in;


        try{
            in=openFileInput(filename);
            if (in==null){return "nothing here";}else {
            byte[]byt=new byte[1024];
            int len=in.read(byt);

            return new String(byt,0,len);}


        } catch (IOException e) {
            e.printStackTrace();
        }
        return "nothing here";
          
    }



    public void save(String data){
        FileOutputStream out;
        BufferedWriter writer=null;
        try{
            out=openFileOutput(filename, Context.MODE_PRIVATE);
            writer =new BufferedWriter(new OutputStreamWriter(out));
            writer.write(data);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer!=null){
                    writer.close();
                }

            }catch (IOException e){
                e.printStackTrace();
            }
        }

    }

}
