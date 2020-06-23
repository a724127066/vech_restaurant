import Vue from 'vue'
import { Button } from 'vant'
import { NavBar } from 'vant';
import { Tabbar, TabbarItem } from 'vant';
import { Divider } from 'vant';

import { Icon } from 'vant';
import { Rate } from 'vant';


import { Sidebar, SidebarItem } from 'vant';
import { Tab, Tabs } from 'vant';
import {
    GoodsAction,
    GoodsActionIcon,
    GoodsActionButton
} from 'vant';
import { Picker } from 'vant';
import { Popup } from 'vant';
import { Toast } from 'vant';
import { List } from 'vant';
import { Field } from 'vant';
import { Loading } from 'vant';
import { Overlay,CellGroup,Dialog} from 'vant';

Vue.use(Overlay,CellGroup,Dialog);

Vue.use(Loading);

Vue.use(Field);

Vue.use(List);

Vue.use(Toast);

Vue.use(Popup);

Vue.use(Picker);

Vue
    .use(GoodsAction)
    .use(GoodsActionIcon)
    .use(GoodsActionButton);

Vue.use(Tab).use(Tabs);

Vue.use(Sidebar);
Vue.use(SidebarItem);

Vue.use(Rate);

Vue.use(Icon);

Vue.use(Divider);

Vue.use(Tabbar).use(TabbarItem);
Vue.use(NavBar);
Vue.use(Button)
