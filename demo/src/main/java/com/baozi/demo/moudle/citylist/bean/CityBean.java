package com.baozi.demo.moudle.citylist.bean;

import com.baozi.treerecyclerview.base.BaseItemData;

import java.util.List;

/**
 * Created by baozi on 2016/12/8.
 */
public class CityBean extends BaseItemData {

    /**
     * citys : [{"areas":[{"areaId":2799,"areaName":"三环以内"},{"areaId":2819,"areaName":"三环到四环之间"},{"areaId":2839,"areaName":"四环到五环之间"},{"areaId":2840,"areaName":"五环到六环之间"},{"areaId":4137,"areaName":"管庄"},{"areaId":4139,"areaName":"北苑"},{"areaId":4211,"areaName":"定福庄"}],"cityId":72,"cityName":"朝阳区"},{"areas":[{"areaId":2848,"areaName":"三环以内"},{"areaId":2849,"areaName":"三环到四环之间"},{"areaId":2850,"areaName":"四环到五环之间"},{"areaId":2851,"areaName":"五环到六环之间"},{"areaId":2852,"areaName":"六环以外"},{"areaId":4134,"areaName":"西三旗"},{"areaId":4209,"areaName":"西二旗"}],"cityId":2800,"cityName":"海淀区"},{"areas":[{"areaId":2853,"areaName":"二环到三环"},{"areaId":2827,"areaName":"内环到二环里"}],"cityId":2801,"cityName":"西城区"},{"areas":[{"areaId":2821,"areaName":"内环到三环里"}],"cityId":2802,"cityName":"东城区"},{"areas":[{"areaId":2842,"areaName":"二环到三环"},{"areaId":2829,"areaName":"一环到二环"}],"cityId":2803,"cityName":"崇文区"},{"areas":[{"areaId":2828,"areaName":"内环到三环里"}],"cityId":2804,"cityName":"宣武区"},{"areas":[{"areaId":34545,"areaName":"六环之外"},{"areaId":34544,"areaName":"五环到六环之间"},{"areaId":2855,"areaName":"三环到四环之间"},{"areaId":2854,"areaName":"二环到三环"},{"areaId":2832,"areaName":"四环到五环之间"}],"cityId":2805,"cityName":"丰台区"},{"areas":[{"areaId":4188,"areaName":"八大处科技园区"},{"areaId":4187,"areaName":"石景山城区"},{"areaId":2831,"areaName":"四环到五环内"}],"cityId":2806,"cityName":"石景山区"},{"areas":[{"areaId":51562,"areaName":"清水镇"},{"areaId":51561,"areaName":"斋堂镇"},{"areaId":51560,"areaName":"雁翅镇"},{"areaId":51559,"areaName":"妙峰山镇"},{"areaId":51558,"areaName":"军庄镇"},{"areaId":51557,"areaName":"王平镇"},{"areaId":51556,"areaName":"潭柘寺镇"},{"areaId":51555,"areaName":"大台镇"},{"areaId":51554,"areaName":"永定镇"},{"areaId":51553,"areaName":"龙泉镇"},{"areaId":51552,"areaName":"城区"}],"cityId":2807,"cityName":"门头沟"},{"areas":[{"areaId":51538,"areaName":"南窖乡"},{"areaId":51537,"areaName":"琉璃河镇"},{"areaId":51536,"areaName":"良乡镇"},{"areaId":51535,"areaName":"河北镇"},{"areaId":51534,"areaName":"韩村河镇"},{"areaId":51532,"areaName":"佛子庄乡"},{"areaId":51531,"areaName":"窦店镇"},{"areaId":51530,"areaName":"大石窝镇"},{"areaId":51529,"areaName":"大安山乡"},{"areaId":51528,"areaName":"城区"},{"areaId":51539,"areaName":"蒲洼乡"},{"areaId":51540,"areaName":"青龙湖镇"},{"areaId":51541,"areaName":"十渡镇"},{"areaId":51542,"areaName":"石楼镇"},{"areaId":51543,"areaName":"史家营乡"},{"areaId":51544,"areaName":"霞云岭乡"},{"areaId":51545,"areaName":"新镇"},{"areaId":51546,"areaName":"阎村镇"},{"areaId":51547,"areaName":"燕山地区"},{"areaId":51548,"areaName":"张坊镇"},{"areaId":51549,"areaName":"长沟镇"},{"areaId":51550,"areaName":"长阳镇"},{"areaId":51551,"areaName":"周口店镇"}],"cityId":2808,"cityName":"房山区"},{"areas":[{"areaId":51216,"areaName":"六环内（马驹桥镇）"},{"areaId":51217,"areaName":"六环外（马驹桥镇）"},{"areaId":51218,"areaName":"永顺镇"},{"areaId":51219,"areaName":"梨园镇"},{"areaId":51220,"areaName":"宋庄镇"},{"areaId":51221,"areaName":"漷县镇"},{"areaId":51222,"areaName":"张家湾镇"},{"areaId":51223,"areaName":"西集镇"},{"areaId":51224,"areaName":"永乐店镇"},{"areaId":51225,"areaName":"潞城镇"},{"areaId":51226,"areaName":"台湖镇"},{"areaId":51227,"areaName":"于家务乡"},{"areaId":51228,"areaName":"中仓街道"},{"areaId":51229,"areaName":"新华街道"},{"areaId":51230,"areaName":"玉桥街道"},{"areaId":51231,"areaName":"北苑街道"},{"areaId":51232,"areaName":"次渠镇"}],"cityId":2809,"cityName":"通州区"},{"areas":[{"areaId":4194,"areaName":"四环至五环之间"},{"areaId":4205,"areaName":"六环以外"},{"areaId":6501,"areaName":"五环至六环之间"},{"areaId":51081,"areaName":"亦庄经济开发区"}],"cityId":2810,"cityName":"大兴区"},{"areas":[{"areaId":51125,"areaName":"北石槽镇"},{"areaId":51126,"areaName":"北务镇"},{"areaId":51127,"areaName":"北小营镇"},{"areaId":51128,"areaName":"大孙各庄镇"},{"areaId":51129,"areaName":"高丽营镇"},{"areaId":51130,"areaName":"光明街道"},{"areaId":51131,"areaName":"后沙峪地区"},{"areaId":51132,"areaName":"空港街道"},{"areaId":51133,"areaName":"李桥镇"},{"areaId":51134,"areaName":"李遂镇"},{"areaId":51135,"areaName":"龙湾屯镇"},{"areaId":51136,"areaName":"马坡地区"},{"areaId":51137,"areaName":"木林镇"},{"areaId":51138,"areaName":"南彩镇"},{"areaId":51139,"areaName":"南法信地区"},{"areaId":51140,"areaName":"牛栏山地区"},{"areaId":51141,"areaName":"仁和地区"},{"areaId":51142,"areaName":"胜利街道"},{"areaId":51143,"areaName":"石园街道"},{"areaId":51144,"areaName":"双丰街道"},{"areaId":51145,"areaName":"天竺地区"},{"areaId":51146,"areaName":"旺泉街道"},{"areaId":51147,"areaName":"杨镇地区"},{"areaId":51148,"areaName":"张镇"},{"areaId":51149,"areaName":"赵全营镇"}],"cityId":2812,"cityName":"顺义区"},{"areas":[{"areaId":2847,"areaName":"郊区"},{"areaId":6115,"areaName":"城区以内"}],"cityId":2814,"cityName":"怀柔区"},{"areas":[{"areaId":2862,"areaName":"城区以外"},{"areaId":6667,"areaName":"城区"}],"cityId":2816,"cityName":"密云区"},{"areas":[{"areaId":2906,"areaName":"城区以外"},{"areaId":4135,"areaName":"六环以内"},{"areaId":4136,"areaName":"城区"}],"cityId":2901,"cityName":"昌平区"},{"areas":[{"areaId":2954,"areaName":"城区以外"},{"areaId":6666,"areaName":"城区"}],"cityId":2953,"cityName":"平谷区"},{"areas":[{"areaId":51505,"areaName":"延庆镇"},{"areaId":51506,"areaName":"城区"},{"areaId":51507,"areaName":"康庄镇"},{"areaId":51508,"areaName":"八达岭镇"},{"areaId":51509,"areaName":"永宁镇"},{"areaId":51510,"areaName":"旧县镇"},{"areaId":51511,"areaName":"张山营镇"},{"areaId":51512,"areaName":"四海镇"},{"areaId":51513,"areaName":"千家店镇"},{"areaId":51514,"areaName":"沈家营镇"},{"areaId":51515,"areaName":"大榆树镇"},{"areaId":51516,"areaName":"井庄镇"},{"areaId":51517,"areaName":"大庄科乡"},{"areaId":51518,"areaName":"刘斌堡乡"},{"areaId":51519,"areaName":"香营乡"},{"areaId":51520,"areaName":"珍珠泉乡"}],"cityId":3065,"cityName":"延庆县"}]
     * provinceId : 1
     * provinceName : 北京
     */

    private int provinceId;
    private String provinceName;
    private List<CitysBean> citys;

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public List<CitysBean> getCitys() {
        return citys;
    }

    public void setCitys(List<CitysBean> citys) {
        this.citys = citys;
    }

    public static class CitysBean extends BaseItemData {
        /**
         * areas : [{"areaId":2799,"areaName":"三环以内"},{"areaId":2819,"areaName":"三环到四环之间"},{"areaId":2839,"areaName":"四环到五环之间"},{"areaId":2840,"areaName":"五环到六环之间"},{"areaId":4137,"areaName":"管庄"},{"areaId":4139,"areaName":"北苑"},{"areaId":4211,"areaName":"定福庄"}]
         * cityId : 72
         * cityName : 朝阳区
         */

        private int cityId;
        private String cityName;
        private List<AreasBean> areas;

        public int getCityId() {
            return cityId;
        }

        public void setCityId(int cityId) {
            this.cityId = cityId;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public List<AreasBean> getAreas() {
            return areas;
        }

        public void setAreas(List<AreasBean> areas) {
            this.areas = areas;
        }

        public static class AreasBean extends BaseItemData {
            /**
             * areaId : 2799
             * areaName : 三环以内
             */

            private int areaId;
            private String areaName;

            public int getAreaId() {
                return areaId;
            }

            public void setAreaId(int areaId) {
                this.areaId = areaId;
            }

            public String getAreaName() {
                return areaName;
            }

            public void setAreaName(String areaName) {
                this.areaName = areaName;
            }

        }
    }
}
