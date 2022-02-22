package ke.co.ideagalore.mvvmpractice.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ke.co.ideagalore.mvvmpractice.Adapters.UserAdapter;
import ke.co.ideagalore.mvvmpractice.Models.User;
import ke.co.ideagalore.mvvmpractice.R;
import ke.co.ideagalore.mvvmpractice.ViewModel.HomeViewModel;

public class Home extends Fragment {

    private HomeViewModel homeViewModel;
    private RecyclerView recyclerView;
    private UserAdapter adapter;
    private EditText edtName;
    private Button btnSaveUser;
    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerview_items);
        edtName = view.findViewById(R.id.edt_name);
        btnSaveUser = view.findViewById(R.id.btn_save);
        progressBar=view.findViewById(R.id.progress_bar);

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
       // homeViewModel.init();
        //initRecyclerView();
        homeViewModel.getUserList();

        homeViewModel.userList.observe(getViewLifecycleOwner(), v->{
            showUsers(v);
        });


        btnSaveUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
                String key = reference.push().getKey();
                Map<String, Object> map = new HashMap<>();
                map.put("name", edtName.getText().toString());
                map.put("id", key);
                if(!edtName.getText().toString().isEmpty()){
                    reference.child(key).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                edtName.setText("");
                            }
                        }
                    });}
                else
                    Toast.makeText(getActivity(), "Field can't be empty", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void showUsers(List<User> users) {
        adapter = new UserAdapter(users, getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter.notifyDataSetChanged();

    }

    private void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {

        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        homeViewModel.clear();
    }
}