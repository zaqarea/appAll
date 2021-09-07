package com.example.StudentsQROrg.Remote;


import com.example.StudentsQROrg.Model.MyResponse;
import com.example.StudentsQROrg.Model.Sender;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {@Headers({
        "Content-Type:application/json", "Authorization:key=AAAArG1k57I:APA91bHREwtNgJDUoKyG5p6UAUPJn4P3fHloeRBM7Uq6xdNSY2SWjjjA1pSek-10RO0V7vM4Bv18tAIt2L7OkIS7BDGf4oOa4EhJoayNTLcRl-09OS91rU1ncmYYjYbvV6g9EWjnoUaZ"
})

@POST("fcm/send")
Call<MyResponse> sendNotification(@Body Sender body);
}
