/*
 * Copyright 2013 serso aka se.solovyev
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
 *
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Contact details
 *
 * Email: se.solovyev@gmail.com
 * Site:  http://se.solovyev.org
 */

package org.solovyev.android.samples.http;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import org.jetbrains.annotations.NotNull;
import org.solovyev.android.http.ImageLoader;
import org.solovyev.android.list.ListItem;
import org.solovyev.android.samples.R;
import org.solovyev.android.view.ViewFromLayoutBuilder;

/**
 * User: serso
 * Date: 8/10/12
 * Time: 2:32 PM
 */
public class HttpListItem implements ListItem {

    @NotNull
    private String uri;

    @NotNull
    private ImageLoader imageLoader;

    public HttpListItem(@NotNull String uri, @NotNull ImageLoader imageLoader) {
        this.uri = uri;
        this.imageLoader = imageLoader;
    }

    @Override
    public OnClickAction getOnClickAction() {
        return null;
    }

    @Override
    public OnClickAction getOnLongClickAction() {
        return null;
    }

    @NotNull
    @Override
    public View updateView(@NotNull Context context, @NotNull View view) {
        if (getTag().equals(view.getTag())) {
            fillView(context, view);
            return view;
        } else {
            return build(context);
        }
    }

    private void fillView(@NotNull Context context, @NotNull View root) {
        final ImageView icon = (ImageView) root.findViewById(R.id.http_item_icon);

        imageLoader.loadImage(uri, icon, R.drawable.icon);

        final TextView text = (TextView) root.findViewById(R.id.http_item_text);
        text.setText(uri);
    }

    @NotNull
    @Override
    public View build(@NotNull Context context) {
        final View root = ViewFromLayoutBuilder.newInstance(R.layout.acl_http_list_item).build(context);
        root.setTag(getTag());
        fillView(context, root);
        return root;
    }

    @NotNull
    private String getTag() {
        return "http_list_item";
    }
}
