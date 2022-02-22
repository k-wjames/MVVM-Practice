package ke.co.ideagalore.mvvmpractice.Repositories;

import java.util.ArrayList;
import java.util.List;

import ke.co.ideagalore.mvvmpractice.Models.User;

public class UserRepository {

    private static UserRepository instance;
    private ArrayList<User> users = new ArrayList<>();

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
        }
        return instance;
    }

    public List<User> getUsers() {
        retrieveUsers();
        return users;
    }

    private void retrieveUsers() {
        users.add(new User("Noah", "123", false));
        users.add(new User("Nancy", "456", false));
        users.add(new User("Lisa", "789", false));
        users.add(new User("Ambetsa", "987", false));
        users.add(new User("Muhia", "879", false));
        users.add(new User("Njeri", "153", false));
        users.add(new User("kamusi", "103", false));
        users.add(new User("Nkirote", "133", false));
      /*  DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Users");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                users.clear();
                for (DataSnapshot userSnapshot:snapshot.getChildren()){
                    User user=userSnapshot.getValue(User.class);
                    users.add(user);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/
    }
}
