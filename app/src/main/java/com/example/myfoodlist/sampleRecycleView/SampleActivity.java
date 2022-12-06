package com.example.myfoodlist.sampleRecycleView;

import android.content.Intent;

public class SampleActivity {

    private Intent intent;
    private String name;

    public Intent getIntent() {
        return intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SampleActivity(Intent intent, String name) {
        this.intent = intent;
        this.name = name;
    }
}
