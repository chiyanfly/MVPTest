package widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Adapter;

/**
 * An extended RecyclerView which supports empty view.
 *
 * <p>
 * Created by ZENG Yuhao. <br>
 * Contact: enzo.zyh@gmail.com
 * </p>
 */

public class RecyclerViewExtended extends RecyclerView {
    private View mEmptyView;
    private AdapterDataObserver mEmptyAdapterDataObserver = new EmptyAdapterDataObserver();

    public RecyclerViewExtended(Context context) {
        super(context);
    }

    public RecyclerViewExtended(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RecyclerViewExtended(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setEmptyView(View emptyView) {
        mEmptyView = emptyView;
    }

    @Override
    public void setAdapter(Adapter adapter) {
        // unregister observer for previous adapter
        Adapter previousAdapter = getAdapter();
        if (previousAdapter != null)
            previousAdapter.unregisterAdapterDataObserver(mEmptyAdapterDataObserver);

        // register observer for new adapter
        if (adapter != null)
            adapter.registerAdapterDataObserver(mEmptyAdapterDataObserver);

        // update adapter
        super.setAdapter(adapter);
    }

    private class EmptyAdapterDataObserver extends AdapterDataObserver {
        public void emptyCheck() {
            Adapter adapter = getAdapter();
            if (adapter != null && mEmptyView != null) {
                if (adapter.getItemCount() == 0) {
                    mEmptyView.setVisibility(VISIBLE);
                    RecyclerViewExtended.this.setVisibility(GONE);
                    // Since mEmptyView is not contained in RecyclerView's layout, a call of requestLayout() of parent
                    // view is required to layout mEmptyView correctly.
//                    mEmptyView.requestLayout();
                } else {
                    mEmptyView.setVisibility(GONE);
                    RecyclerViewExtended.this.setVisibility(VISIBLE);
                }
            }
        }

        @Override
        public void onChanged() {
            emptyCheck();
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            emptyCheck();
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            emptyCheck();
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            emptyCheck();
        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            emptyCheck();
        }
    }
}
