package com.xfcar.driver.model.adapterbean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author linky
 */
public class PagerBean {
    public String imgUrl;

    public static List<PagerBean> mockPagers() {
//        int[] a = new int[]{R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};

        String[] a = new String[]{
                "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1018687198,174914688&fm=15&gp=0.jpg",
                "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2149310024,255707060&fm=26&gp=0.jpg",
                "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2105937605,2474047205&fm=15&gp=0.jpg"
        };

        List<PagerBean> pbs = new ArrayList<>();
        for (String aa : a) {
            PagerBean pb = new PagerBean();
            pb.imgUrl = aa;
            pbs.add(pb);
        }

        return pbs;
    }
}
