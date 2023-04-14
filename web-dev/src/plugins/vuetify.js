import Vue from 'vue';
import Vuetify from 'vuetify/lib/framework';

Vue.use(Vuetify);

export default new Vuetify({
    icons: {
        iconfont: 'md', // 'mdi' || 'mdiSvg' || 'md' || 'fa' || 'fa4' || 'faSvg',
        theme: { disable: true }
    },
    theme: {
      themes: {
          light: {
              purple: "#7700FF",
              orange: "#FF4F12"
          }
      }
    },
    breakpoint: {
        xs: true,
        sm: true,
        md: true,
        lg: true,
        xl: true,

        thresholds: {
            xs: 375,
            sm: 768,
            md: 1024,
            lg: 1440
        }
    }
});
