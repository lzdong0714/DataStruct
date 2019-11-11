package algorithm.visual;

import java.awt.Point;


public class Circle {
    public int x,y;
    private int r;
    public int vx,vy;
    public Circle(int x,int y,int r,int vx,int vy) {
        this.x=x;
        this.y=y;
        this.r=r;
        this.vx=vx;
        this.vy=vy;
    }

    public boolean isFilled=false;

    public int getR() {
        return r;
    }

    public boolean contain(Point p) {
        return (x-p.x)*(x-p.x)+(y-p.y)*(y-p.y)<=r*r;
    }

    public void move(int minx,int miny,int maxx,int maxy) {
        x+=vx;
        y+=vy;
        chechCollison(minx, miny, maxx, maxy);
    }

    private void chechCollison(int minx,int miny,int maxx,int maxy) {
        if(x-r<minx) 	{x=r;		vx=-vx;}
        if(x+r>maxx)	{x=maxx-r;	vx=-vx;}
        if(y-r<miny)	{y=r;		vy=-vy;}
        if(y+r>maxy)	{y=maxy-r;	vy=-vy;}
    }
}
