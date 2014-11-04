package sm.com.todolist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MyActivity extends Activity {

    Context ctxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_my);
        ctxt = this;

        Button ourButton = (Button) findViewById(R.id.button);
        ourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ourIntent = new Intent(ctxt, ToDoList.class);
                startActivity(ourIntent);
            }
        });
    }

}
