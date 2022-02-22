package ke.co.ideagalore.mvvmpractice.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import ke.co.ideagalore.mvvmpractice.Models.User;
import ke.co.ideagalore.mvvmpractice.Repositories.UserRepository;

public class HomeViewModel extends ViewModel {

    public HomeViewModel() {

        userRepository=UserRepository.getInstance();

    }

    public MutableLiveData<List<User>> userList=new MutableLiveData<>();
    private UserRepository userRepository;
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

 /*   public void init() {
        if (userList != null) {
            return;
        }
        userRepository = UserRepository.getInstance();


    }*/

    public void clear() {
        if (userList != null) {
            userList.setValue(null);
            return;
        };
    }

    public void getUserList() {
        userList.setValue(userRepository.getUsers());
    }

}
