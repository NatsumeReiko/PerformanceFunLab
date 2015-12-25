package lab.fun.performance.javaheap;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import lab.fun.performance.R;
import lab.fun.performance.Utility.Constant;
import lab.fun.performance.Utility.ExecuteCallable;
import lab.fun.performance.Utility.ExecuteCallableInterface;

public class HeapObjectCountTest extends AppCompatActivity implements View.OnClickListener {
    private List<TargetHeapObjectCountTest> targetList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_heap_object_count_test);
//        ButterKnife.bind(this);

        initializeView();

    }

    /**
     * initialize views
     */
    private void initializeView() {
        findViewById(R.id.make_target_data).setOnClickListener(this);

    }

    /**
     * make test data
     */
    private void makeTargetObjects() {

        try {
            ExecutorService service = Executors.newSingleThreadExecutor();
            ExecuteCallable sumTask = new ExecuteCallable(new ExecuteCallableInterface() {
                @Override
                public List<TargetHeapObjectCountTest> executeCall() {
                    /*do real thing here*/
                    List<TargetHeapObjectCountTest> resultList = new ArrayList<>();

                    for (int index = 0; index < Constant.SAMPLE_COUNT_SMALL; index++) {
                        resultList.add(new TargetHeapObjectCountTest("name" + index, index));
                    }
                    return resultList;
                }
            });
            Future<List<TargetHeapObjectCountTest>> future = service.submit(sumTask);
            targetList = future.get();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //    @OnClick({R.id.make_target_data})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.make_target_data: {
                /*make targets for memory analyze */
                makeTargetObjects();
            }
            break;

        }

    }
}
