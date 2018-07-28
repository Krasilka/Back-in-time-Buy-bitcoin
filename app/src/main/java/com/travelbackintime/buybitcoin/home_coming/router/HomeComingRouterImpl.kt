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

package com.travelbackintime.buybitcoin.home_coming.router

import android.net.Uri
import android.support.v4.app.ShareCompat
import android.support.v7.app.AppCompatActivity
import bitcoin.backintime.com.backintimebuybitcoin.R
import com.facebook.share.model.ShareHashtag
import com.facebook.share.model.ShareLinkContent
import com.facebook.share.widget.ShareDialog
import com.travelbackintime.buybitcoin.home_coming.view.HomeComingFragment
import com.travelbackintime.buybitcoin.time_travel.view.createTimeTravelFragment
import com.twitter.sdk.android.tweetcomposer.TweetComposer
import javax.inject.Inject

class HomeComingRouterImpl @Inject constructor(fragment: HomeComingFragment) : HomeComingRouter {

    private val activity = fragment.activity as AppCompatActivity

    override fun openTimeTravelActivity() {
        activity.supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, createTimeTravelFragment())
                .commit()
    }

    override fun shareWithFriends(textToShare: String) {
        ShareCompat.IntentBuilder.from(activity)
                .setHtmlText(textToShare)
                .setType("infoText/html")
                .startChooser()
    }

    override fun shareToTwitter(textToShare: String) {
        val textToShareBuilder = StringBuilder(textToShare)
        textToShareBuilder.append(" #")
        textToShareBuilder.append(activity.getString(R.string.text_hashtag))
        val builder = TweetComposer.Builder(activity)
                .text(textToShareBuilder.toString())
        builder.show()
    }

    override fun shareToFaceBook(googlePlayLink: String) {
        val content = ShareLinkContent.Builder()
                .setContentUrl(Uri.parse(googlePlayLink))
                .setShareHashtag(ShareHashtag.Builder().setHashtag(activity.getString(R.string.text_hashtag)).build())
                .build()
        ShareDialog.show(activity, content)
    }
}
