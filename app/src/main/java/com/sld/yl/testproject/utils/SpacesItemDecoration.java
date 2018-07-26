package com.sld.yl.testproject.utils;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

/**
 * Created by Au61 on 2016/1/15.
 */
public class SpacesItemDecoration extends RecyclerView.ItemDecoration {

    private SpacesItemDecorationEntrust mEntrust;
    private int mColor;
    private int leftRight;
    private int topBottom;


    public SpacesItemDecoration(int leftRight, int topBottom) {
        this.leftRight = leftRight;
        this.topBottom = topBottom;
    }

    public SpacesItemDecoration(int leftRight, int topBottom, int mColor) {
        this(leftRight, topBottom);
        this.mColor = mColor;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (mEntrust == null) {
            mEntrust = getEntrust(parent.getLayoutManager());
        }
        mEntrust.onDraw(c, parent, state);
        super.onDraw(c, parent, state);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (mEntrust == null) {
            mEntrust = getEntrust(parent.getLayoutManager());
        }
        mEntrust.getItemOffsets(outRect, view, parent, state);
    }

    private SpacesItemDecorationEntrust getEntrust(RecyclerView.LayoutManager manager) {
        SpacesItemDecorationEntrust entrust = null;
        //要注意这边的GridLayoutManager是继承LinearLayoutManager，所以要先判断GridLayoutManager
        if (manager instanceof GridLayoutManager) {
            entrust = new GridEntrust(leftRight, topBottom, mColor);
        } else if (manager instanceof StaggeredGridLayoutManager) {
            entrust = new StaggeredGridEntrust(leftRight, topBottom, mColor);
        } else {//其他的都当做Linear来进行计算
            entrust = new LinearEntrust(leftRight, topBottom, mColor);
        }
        return entrust;
    }

    public class GridEntrust extends SpacesItemDecorationEntrust {


        public GridEntrust(int leftRight, int topBottom, int mColor) {
            super(leftRight, topBottom, mColor);
        }

        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
            final GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
            final GridLayoutManager.SpanSizeLookup lookup = layoutManager.getSpanSizeLookup();

            if (mDivider == null || layoutManager.getChildCount() == 0) {
                return;
            }
            //判断总的数量是否可以整除
            int spanCount = layoutManager.getSpanCount();

            int left;
            int right;
            int top;
            int bottom;

            final int childCount = parent.getChildCount();
            if (layoutManager.getOrientation() == GridLayoutManager.VERTICAL) {

                for (int i = 0; i < childCount; i++) {
                    final View child = parent.getChildAt(i);
                    final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
                    //得到它在总数里面的位置
                    final int position = parent.getChildAdapterPosition(child);
                    //获取它所占有的比重
                    final int spanSize = lookup.getSpanSize(position);
                    //获取每排的位置
                    final int spanIndex = lookup.getSpanIndex(position, layoutManager.getSpanCount());
                    //将带有颜色的分割线处于中间位置
                    final float centerLeft = (layoutManager.getLeftDecorationWidth(child) + 1 - leftRight) / 2;
                    final float centerTop = (layoutManager.getBottomDecorationHeight(child) + 1 - topBottom) / 2;
                    //判断是否为第一排
                    boolean isFirst = position + spanSize <= layoutManager.getSpanCount();
                    //画上边的，第一排不需要上边的,只需要在最左边的那项的时候画一次就好
                    if (!isFirst && spanIndex == 0) {
                        left = layoutManager.getLeftDecorationWidth(child);
                        right = parent.getWidth() - layoutManager.getLeftDecorationWidth(child);

                        top = (int) (child.getTop() - centerTop) - topBottom;
                        bottom = top + topBottom;
                        mDivider.setBounds(left, top, right, bottom);
                        mDivider.draw(c);
                    }
                    //最右边的一排不需要右边的
                    boolean isRight = spanIndex + spanSize == spanCount;
                    if (!isRight) {
                        //计算右边的
                        left = (int) (child.getRight() + centerLeft);
                        right = left + leftRight;
                        top = child.getTop();

                        if (position + spanSize - 1 >= spanCount) {
                            top -= centerTop;
                        }
                        bottom = (int) (child.getBottom() + centerTop);
                        mDivider.setBounds(left, top, right, bottom);
                        mDivider.draw(c);
                    }
                }
            } else {
                for (int i = 0; i < childCount; i++) {
                    final View child = parent.getChildAt(i);
                    final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
                    //得到它在总数里面的位置
                    final int position = parent.getChildAdapterPosition(child);
                    //获取它所占有的比重
                    final int spanSize = lookup.getSpanSize(position);
                    //获取每排的位置
                    final int spanIndex = lookup.getSpanIndex(position, layoutManager.getSpanCount());
                    //将带有颜色的分割线处于中间位置
                    final float centerLeft = (layoutManager.getRightDecorationWidth(child) + 1 - leftRight) / 2;
                    final float centerTop = (layoutManager.getTopDecorationHeight(child) + 1 - topBottom) / 2;
                    //判断是否为第一列
                    boolean isFirst = position + spanSize <= layoutManager.getSpanCount();
                    //画左边的，第一排不需要左边的,只需要在最上边的那项的时候画一次就好
                    if (!isFirst && spanIndex == 0) {
                        left = (int) (child.getLeft() - centerLeft) - leftRight;
                        right = left + leftRight;

                        top = layoutManager.getRightDecorationWidth(child);
                        bottom = parent.getHeight() - layoutManager.getTopDecorationHeight(child);
                        mDivider.setBounds(left, top, right, bottom);
                        mDivider.draw(c);
                    }
                    //最下的一排不需要下边的
                    boolean isRight = spanIndex + spanSize == spanCount;
                    if (!isRight) {
                        //计算右边的
                        left = child.getLeft();
                        if (position + spanSize - 1 >= spanCount) {
                            left -= centerLeft;
                        }
                        right = (int) (child.getRight() + centerTop);

                        top = (int) (child.getBottom() + centerLeft);
                        bottom = top + leftRight;
                        mDivider.setBounds(left, top, right, bottom);
                        mDivider.draw(c);
                    }
                }
            }
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
            final GridLayoutManager.LayoutParams lp = (GridLayoutManager.LayoutParams) view.getLayoutParams();
            final int childPosition = parent.getChildAdapterPosition(view);
            final int spanCount = layoutManager.getSpanCount();

            if (layoutManager.getOrientation() == GridLayoutManager.VERTICAL) {
                if (childPosition + lp.getSpanSize() - 1 < spanCount) {//第一排的需要上面
                    outRect.top = topBottom;
                }
                if (lp.getSpanIndex() + lp.getSpanSize() == spanCount) {//最边上的需要右边,这里需要考虑到一个合并项的问题
                    outRect.right = leftRight;
                }
                outRect.bottom = topBottom;
                outRect.left = leftRight;
            } else {
                if (childPosition + lp.getSpanSize() - 1 < spanCount) {//第一排的需要left
                    outRect.left = leftRight;
                }
                if (lp.getSpanIndex() + lp.getSpanSize() == spanCount) {//最边上的需要bottom
                    outRect.bottom = topBottom;
                }
                outRect.right = leftRight;
                outRect.top = topBottom;
            }
        }


    }

    public class LinearEntrust extends SpacesItemDecorationEntrust {


        public LinearEntrust(int leftRight, int topBottom, int mColor) {
            super(leftRight, topBottom, mColor);
        }

        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
            LinearLayoutManager layoutManager = (LinearLayoutManager) parent.getLayoutManager();
            //没有子view或者没有没有颜色直接return
            if (mDivider == null || layoutManager.getChildCount() == 0) {
                return;
            }
            int left;
            int right;
            int top;
            int bottom;
            final int childCount = parent.getChildCount();
            if (layoutManager.getOrientation() == GridLayoutManager.VERTICAL) {
                for (int i = 0; i < childCount - 1; i++) {
                    final View child = parent.getChildAt(i);
                    final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
                    //将有颜色的分割线处于中间位置
                    final float center = (layoutManager.getTopDecorationHeight(child) + 1 - topBottom) / 2;
                    //计算下边的
                    left = layoutManager.getLeftDecorationWidth(child);
                    right = parent.getWidth() - layoutManager.getLeftDecorationWidth(child);
                    top = (int) (child.getBottom() + center);
                    bottom = top + topBottom;
                    mDivider.setBounds(left, top, right, bottom);
                    mDivider.draw(c);
                }
            } else {
                for (int i = 0; i < childCount - 1; i++) {
                    final View child = parent.getChildAt(i);
                    final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
                    //将有颜色的分割线处于中间位置
                    final float center = (layoutManager.getLeftDecorationWidth(child) + 1 - leftRight) / 2;
                    //计算右边的
                    left = (int) (child.getRight() + center);
                    right = left + leftRight;
                    top = layoutManager.getTopDecorationHeight(child);
                    bottom = parent.getHeight() - layoutManager.getTopDecorationHeight(child);
                    mDivider.setBounds(left, top, right, bottom);
                    mDivider.draw(c);
                }
            }

        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            LinearLayoutManager layoutManager = (LinearLayoutManager) parent.getLayoutManager();
            //竖直方向的
            if (layoutManager.getOrientation() == LinearLayoutManager.VERTICAL) {
                //最后一项需要 bottom
                if (parent.getChildAdapterPosition(view) == layoutManager.getItemCount() - 1) {
                    outRect.bottom = topBottom;
                }
                outRect.top = topBottom;
                outRect.left = leftRight;
                outRect.right = leftRight;
            } else {
                //最后一项需要right
                if (parent.getChildAdapterPosition(view) == layoutManager.getItemCount() - 1) {
                    outRect.right = leftRight;
                }
                outRect.top = topBottom;
                outRect.left = leftRight;
                outRect.bottom = topBottom;
            }
        }
    }


    public class StaggeredGridEntrust extends SpacesItemDecorationEntrust {


        public StaggeredGridEntrust(int leftRight, int topBottom, int mColor) {
            super(leftRight, topBottom, mColor);

        }

        @Override
        void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
            //TODO 因为排列的不确定性，暂时没找到比较好的处理方式
//        StaggeredGridLayoutManager layoutManager = (StaggeredGridLayoutManager) parent.getLayoutManager();
//        if (mDivider == null || layoutManager.getChildCount() == 0) {
//            return;
//        }
//        int left;
//        int right;
//        int top;
//        int bottom;
//
//        final int spanCount = layoutManager.getSpanCount();
//        final int childCount = parent.getChildCount();
//
//        //获取最后显示的几项
//        int[] intos = new int[spanCount];
//        layoutManager.findLastVisibleItemPositions(intos);
//
//        //换个思路，每个item都画边和右边，当然第一排的不需要上边，最右边的不需要右边
//        if (layoutManager.getOrientation() == StaggeredGridLayoutManager.VERTICAL) {
//            for (int i = 0; i < childCount; i++) {
//                final View child = parent.getChildAt(i);
//                final StaggeredGridLayoutManager.LayoutParams params = (StaggeredGridLayoutManager.LayoutParams) child.getLayoutParams();
//                //得到它在总数里面的位置
//                final int position = parent.getChildAdapterPosition(child);
//                //它在每列的位置
//                final int spanPosition = params.getSpanIndex();
//                //将带有颜色的分割线处于中间位置
//                final float centerLeft = (layoutManager.getLeftDecorationWidth(child) + 1 - leftRight) / 2;
//                final float centerTop = (layoutManager.getBottomDecorationHeight(child) + 1 - topBottom) / 2;
//                //画上边的
//                if (position > spanCount - 1) {
//                    left = child.getLeft() + params.leftMargin;
//                    if (spanPosition > 0) {
//                        left -= centerLeft;
//                    }
//                    right = child.getRight() + params.rightMargin;
//                    if (spanPosition + 1 != spanCount) {//最右边的不需要那一丢丢
//                        right += centerLeft;
//                    }
//                    top = (int) (child.getTop() + params.topMargin - centerTop - topBottom);
//                    bottom = top + topBottom;
//                    mDivider.setBounds(left, top, right, bottom);
//                    mDivider.draw(c);
//                }
//                //画右边的
//                if ((spanPosition + 1) % spanCount != 0) {
//                    left = (int) (child.getRight() + params.rightMargin + centerLeft);
//                    right = left + leftRight;
//                    top = child.getTop() + params.topMargin;
//                    //第一排的不需要上面那一丢丢
//                    if (position > spanCount - 1) {//换个思路，都给top了
//                        top -= centerTop + centerTop + topBottom;
//                    }
//                    bottom = child.getBottom() + params.bottomMargin;
//                    mDivider.setBounds(left, top, right, bottom);
//                    mDivider.draw(c);
//                }
//            }
//
//        } else {
//            for (int i = 0; i < childCount; i++) {
//                final View child = parent.getChildAt(i);
//                final StaggeredGridLayoutManager.LayoutParams params = (StaggeredGridLayoutManager.LayoutParams) child.getLayoutParams();
//                //得到它在总数里面的位置
//                final int position = parent.getChildAdapterPosition(child);
//                //它在每列的位置
//                final int spanPosition = params.getSpanIndex();
//                //将带有颜色的分割线处于中间位置
//                final float centerLeft = (layoutManager.getRightDecorationWidth(child) + 1 - leftRight) / 2;
//                final float centerTop = (layoutManager.getTopDecorationHeight(child) + 1 - topBottom) / 2;
//                //画左边
//                if (position > spanCount - 1) {
//                    left = (int) (child.getLeft() + params.leftMargin - centerLeft - leftRight);
//                    right = left + leftRight;
//                    top = (int) (child.getTop() + params.topMargin - centerTop);
//                    if (spanPosition == 0) {
//                        top += centerTop;
//                    }
//                    bottom = child.getBottom() + params.bottomMargin;
//                    if (spanPosition + 1 != spanCount) {
//                        bottom += centerTop;
//                    }
//                    mDivider.setBounds(left, top, right, bottom);
//                    mDivider.draw(c);
//                }
//                //画上面的
//                if (spanPosition > 0) {
//                    left = child.getLeft() + params.leftMargin;
//                    if (position > spanCount - 1) {//换个思路，都给left了
//                        left -= centerLeft + centerLeft + leftRight;
//                    }
//                    right = child.getRight() + params.rightMargin;
//                    top = (int) (child.getTop() + params.bottomMargin - centerTop - topBottom);
//                    bottom = top + topBottom;
//                    mDivider.setBounds(left, top, right, bottom);
//                    mDivider.draw(c);
//                }
//            }
//        }
        }

        @Override
        void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            StaggeredGridLayoutManager layoutManager = (StaggeredGridLayoutManager) parent.getLayoutManager();
            final StaggeredGridLayoutManager.LayoutParams lp = (StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams();
            final int childPosition = parent.getChildAdapterPosition(view);
            final int spanCount = layoutManager.getSpanCount();
            final int count = lp.isFullSpan() ? layoutManager.getSpanCount() : 1;

            if (layoutManager.getOrientation() == GridLayoutManager.VERTICAL) {

                if (childPosition + count - 1 < spanCount) {//第一排的需要上面
                    outRect.top = topBottom;
                }
                if (lp.getSpanIndex() + count == spanCount) {//最边上的需要右边,这里需要考虑到一个合并项的问题
                    outRect.right = leftRight;
                }
                outRect.bottom = topBottom;
                outRect.left = leftRight;

            } else {
                if (childPosition + count - 1 < spanCount) {//第一排的需要left
                    outRect.left = leftRight;
                }
                if (lp.getSpanIndex() + count == spanCount) {//最边上的需要bottom
                    outRect.bottom = topBottom;
                }
                outRect.right = leftRight;
                outRect.top = topBottom;
            }
        }
    }


}