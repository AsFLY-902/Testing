public class Search{
    private EditText mSearchField;
    private ImageButton mSearchBtn;
	private RecyclerView mResultList;
	private ReferenceAdapter mAdapter;
	private final List<Reference> list =  new ArrayList<>();

    private DatabaseReference mDatabase;
    private Query query;
 
		mDatabase = FirebaseDatabase.getInstance().getReference().child("app").child("complaints");


        mResultList.setLayoutManager(new LinearLayoutManager(this));
        mResultList.setAdapter(mAdapter);
        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            String searchText = mSearchField.getText().toString().toLowerCase().replaceAll("-","").replaceAll(" ","");
            list.clear();
            loadPosts(searchText);

            }
        });
       }
    private void loadPosts(String title,String year,String cell,String description) {

        query = mDatabase.orderByChild("title").startAt(title).endAt(title+"\uf8ff");
		query = mDatabase.orderByChild("year").startAt(title).endAt(year+"\uf8ff");
		query = mDatabase.orderByChild("cell").startAt(title).endAt(cell+"\uf8ff");
		query = mDatabase.orderByChild("description").startAt(title).endAt(description+"\uf8ff");

        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Reference reference =  dataSnapshot.getValue(Reference.class);
                list.add(reference);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }