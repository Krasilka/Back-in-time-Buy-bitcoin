/*
 * Copyright 2018 Andrey Tolpeev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.travelbackintime.buybitcoin.splash.router

import android.support.v7.app.AppCompatActivity
import bitcoin.backintime.com.backintimebuybitcoin.R
import com.travelbackintime.buybitcoin.splash.view.SplashFragment
import com.travelbackintime.buybitcoin.time_travel.view.TimeTravelFragment
import com.travelbackintime.buybitcoin.utils.addFragmentFadeTransitions
import javax.inject.Inject

interface SplashRouter {

    fun openTimeTravelFragment()
}

class SplashRouterImpl @Inject constructor(fragment: SplashFragment) : SplashRouter {

    val activity: AppCompatActivity = fragment.activity as AppCompatActivity

    override fun openTimeTravelFragment() {
        val fragment = TimeTravelFragment.create()
        addFragmentFadeTransitions(fragment, activity.applicationContext)
        activity.supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, fragment)
                .commitAllowingStateLoss()
    }
}