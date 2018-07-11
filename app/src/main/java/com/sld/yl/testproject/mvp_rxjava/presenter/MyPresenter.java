/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sld.yl.testproject.mvp_rxjava.presenter;

import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;

import com.sld.yl.testproject.mvp_rxjava.contractor.TasksContract;
import com.sld.yl.testproject.mvp_rxjava.schedulers.BaseSchedulerProvider;

import io.reactivex.disposables.CompositeDisposable;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * UI as required.
 */
public class MyPresenter implements TasksContract.Presenter {

/*    @NonNull
    private final TasksRepository mTasksRepository;*/

    @NonNull
    private final TasksContract.View mTasksView;

    @NonNull
    private final BaseSchedulerProvider mSchedulerProvider;


    private boolean mFirstLoad = true;

    @NonNull
    private CompositeDisposable mCompositeDisposable;

    public MyPresenter(/*@NonNull TasksRepository tasksRepository,*///moudle层绑定
                                                                    @NonNull TasksContract.View tasksView,
                                                                    @NonNull BaseSchedulerProvider schedulerProvider) {
        //  mTasksRepository = checkNotNull(tasksRepository, "tasksRepository cannot be null");
        mTasksView = checkNotNull(tasksView, "tasksView cannot be null!");
        mSchedulerProvider = checkNotNull(schedulerProvider, "schedulerProvider cannot be null");

        mCompositeDisposable = new CompositeDisposable();
        mTasksView.setPresenter(this);
    }


    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
        mCompositeDisposable.clear();
    }


    @Override
    public void showDialog() {
        mTasksView.showDiaglog();
    }
}
