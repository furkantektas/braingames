package com.furkantektas.braingames.datatypes;

import com.furkantektas.braingames.R;

/**
 * Created by Furkan Tektas on 11/20/14.
 */
public class ShapeMatch {
    public Shape shape;

    public ShapeMatch(Shape shape) {
        this.shape = shape;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (o == this) return true;
        if (!(o instanceof ShapeMatch))return false;
        ShapeMatch sOther = (ShapeMatch)o;
        return (sOther.shape.resId == shape.resId);
    }

    public static enum Shape {
        BAG(R.drawable.ic_sm_bag),
        CAR(R.drawable.ic_sm_car),
        CLOUD(R.drawable.ic_sm_cloud),
        HOME(R.drawable.ic_sm_home),
        KEY(R.drawable.ic_sm_key);

        private int resId;

        private Shape(int resId) {
            this.resId = resId;
        }

        public int getResId() {
            return resId;
        }
    }

    public static Shape generateShape(int ind) {
        int i = 0;
        Shape[] shapes = Shape.values();

        if(ind >= shapes.length)
            ind = (ind + shapes.length) % shapes.length;

        return shapes[ind];
    }

}
