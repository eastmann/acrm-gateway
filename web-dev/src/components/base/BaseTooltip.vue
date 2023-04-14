<template>
<v-tooltip top class="white">
  <template v-slot:activator="{ on, attrs }">
    <v-btn icon text
        v-bind="attrs" v-on="on">
      <v-icon>{{ icons.mdiInformationVariant }}</v-icon>
    </v-btn>
  </template>
  <span>{{ msg }}</span>
</v-tooltip>
</template>

<script>
import { VTooltip, VBtn, VIcon } from "vuetify/lib/components";
import { mdiInformationVariant } from "@mdi/js";

export default {
  name: "BaseTooltip",
  data() {
    return {
      icons: { mdiInformationVariant }
    }
  },
  props: {
    msg: {
      type: String,
      default: "Здесь будет текст!"
    }
  },
  components: {
    "v-tooltip" : VTooltip,
    "v-btn" : VBtn,
    "v-icon" : VIcon
  }
}
</script>

<style scoped lang="less">
@import "./css/base.less";
.v-btn--icon {
  width: 20px !important;
  height: 20px !important;
}

.v-btn::before {
  background-color: #0042ED;
  opacity: 0.1;
}
</style>