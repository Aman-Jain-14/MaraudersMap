private boolean calculateThreeCircleIntersection(double x0, double y0, double r0,
                                                 double x1, double y1, double r1,
                                                 double x2, double y2, double r2)
{
    double a, dx, dy, d, h, rx, ry;
    double point2_x, point2_y;

    /* dx and dy are the vertical and horizontal distances between
    * the circle centers.
    */
    dx = x1 - x0;
    dy = y1 - y0;

    /* Determine the straight-line distance between the centers. */
    d = Math.sqrt((dy*dy) + (dx*dx));

    /* Check for solvability. */
    if (d > (r0 + r1))
    {
        /* no solution. circles do not intersect. */
        return false;
    }
    if (d < Math.abs(r0 - r1))
    {
        /* no solution. one circle is contained in the other */
        return false;
    }

    /* 'point 2' is the point where the line through the circle
    * intersection points crosses the line between the circle
    * centers.
    */

    /* Determine the distance from point 0 to point 2. */
    a = ((r0*r0) - (r1*r1) + (d*d)) / (2.0 * d) ;

    /* Determine the coordinates of point 2. */
    point2_x = x0 + (dx * a/d);
    point2_y = y0 + (dy * a/d);

    /* Determine the distance from point 2 to either of the
    * intersection points.
    */
    h = Math.sqrt((r0*r0) - (a*a));

    /* Now determine the offsets of the intersection points from
    * point 2.
    */
    rx = -dy * (h/d);
    ry = dx * (h/d);

    /* Determine the absolute intersection points. */
    double intersectionPoint1_x = point2_x + rx;
    double intersectionPoint2_x = point2_x - rx;
    double intersectionPoint1_y = point2_y + ry;
    double intersectionPoint2_y = point2_y - ry;

    Log.d("INTERSECTION Circle1 AND Circle2:", "(" + intersectionPoint1_x + "," + intersectionPoint1_y + ")" + " AND (" + intersectionPoint2_x + "," + intersectionPoint2_y + ")");

    /* Lets determine if circle 3 intersects at either of the above intersection points. */
    dx = intersectionPoint1_x - x2;
    dy = intersectionPoint1_y - y2;
    double d1 = Math.sqrt((dy*dy) + (dx*dx));


    dx = intersectionPoint2_x - x2;
    dy = intersectionPoint2_y - y2;
    double d2 = Math.sqrt((dy*dy) + (dx*dx));

    if(Math.abs(d1 - r2) < EPSILON) {
        Log.d("INTERSECTION Circle1 AND Circle2 AND Circle3:", "(" + intersectionPoint1_x + "," + intersectionPoint1_y + ")");
    }
    else if(Math.abs(d2 - r2) < EPSILON) {
        Log.d("INTERSECTION Circle1 AND Circle2 AND Circle3:", "(" + intersectionPoint2_x + "," + intersectionPoint2_y + ")"); //here was an error
    }
    else {
        Log.d("INTERSECTION Circle1 AND Circle2 AND Circle3:", "NONE");
    }
    return true;
}

calculateThreeCircleIntersection(-2.0, 0.0, 2.0, // circle 1 (center_x, center_y, radius)
                                  1.0, 0.0, 1.0, // circle 2 (center_x, center_y, radius)
                                  0.0, 4.0, 4.0);// circle 3 (center_x, center_y, radius)