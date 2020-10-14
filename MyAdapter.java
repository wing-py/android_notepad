public class MyAdapter extends RecyclerView.Adapter<MyAdapter.VH> {
    ArrayList<String> adapterlist;
    Context context;



    public MyAdapter(Context context, ArrayList<String> adapterlist){this.context=context;this.adapterlist=adapterlist; }
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { View view = View.inflate(context,R.layout.list_item,null);
        return new VH(view); }
    @Override
        public void onBindViewHolder(VH vh, final int position){TextView textView=vh.itemView.findViewById(R.id.item_id);textView.setText("    "+adapterlist.get(position));
        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, Note.class);
                intent.putExtra("filename",adapterlist.get(position));
                context.startActivity(intent);
            }
        });
        vh.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                context.deleteFile(adapterlist.get(position));
                adapterlist.remove(position);
                notifyDataSetChanged();
                Toast.makeText(context, "you have deleted item   "+String.valueOf(position), Toast.LENGTH_SHORT).show();

                return false;
            }
        });
    }
    @Override
        public int getItemCount(){return adapterlist.size();}

    public class VH extends RecyclerView.ViewHolder{ public VH(View itemView) { super(itemView);TextView tv=itemView.findViewById(R.id.item_id); }}
}
