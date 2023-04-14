// Страница для просмотра UI-компонентов, не выводить на прод
<template>

  <v-container>
    <!--    <v-layout tag="header" justify-center>-->
    <!--      <v-flex class="d-flex justify-space-between align-center">-->
    <!--        <div class="logo">-->
    <!--          <v-img contain alt="логотип"-->
    <!--                 src="../../asserts/img/login/rtk_logo.png"-->
    <!--                 height="40px" width="40px" />-->
    <!--          <span>Ростелеком</span>-->
    <!--        </div>-->
    <!--      </v-flex>-->
    <!--    </v-layout>-->
    <v-layout fill-height tag="main" justify-center>
      <v-flex>
        <h2 class="py-5 text-left">Базовые компоненты</h2>

        <p>UI-компонент "Подсказка" на основе <a href="https://v2.vuetifyjs.com/en/components/tooltips/#usage"
                                                 target="_blank">v-tooltip</a>
          Vuetify</p>
        <v-card class="pa-5">
          <base-tooltip/>
        </v-card>

        <p class="mt-5">UI-компонет на основе <a href="https://vuetifyjs.com/en/api/v-data-table/"
                                                 target="_blank">v-data-table</a>
          Vuetify</p>

        <ExpandBox header="Основная таблица"
                   :enabled="false"
                   :count="itemsLength"
                   :expanded="true"
                   @on-click-setting="onShowSetting()"
                   @on-search="onSearchName"
        >

          <template v-slot:setting>
            <setting :columns="headers"
                     :show-setting="showSetting"
                     @on-click-setting="onShowSetting()"/>
          </template>

          <base-data-table
              :headers-prop="headersOneLevel"
              :items-prop="items"
              :options-prop="options"
              :search-prop="searchText"
              click-row-prop
              @clickRow="handlerClick"
              show-pagination-prop
              :custom-sort-prop="customSort"
          >
          </base-data-table>

        </ExpandBox>


        <ExpandBox header="Основная таблица c 2х-уровневой шапкой"
                   :enabled="false"
                   :count="itemsLength"
                   :expanded="true"
                   @on-click-setting="onShowSetting()"
                   @on-search="onSearchName"
        >

          <template v-slot:setting>
            <setting :columns="headers"
                     :show-setting="showSetting"
                     @on-click-setting="onShowSetting()"/>
          </template>

          <base-data-table
              :headers-prop="headers"
              :items-prop="items"
              :options-prop="options"
              :search-prop="searchText"
              click-row-prop
              @clickRow="handlerClick"
              show-pagination-prop
              :custom-sort-prop="customSort"
          >
          </base-data-table>

        </ExpandBox>

        <ExpandBox header="Таблица в таблице (expanded table)"
                   :enabled="false"
                   :count="itemsLength"
                   :expanded="true"
                   @on-click-setting="onShowSettingExpand()"
                   @on-search="onSearchNameExpand"
        >

          <template v-slot:setting>
            <setting :columns="headers"
                     :show-setting="showSettingExpand"
                     @on-click-setting="onShowSettingExpand()"/>
          </template>

          <base-expanded-data-table
              :headers-prop="headers"
              :items-prop="items"
              :options-prop="options"
              :show-expand-prop="true"
              :expanded-prop="expanded"
              :search-prop="searchTextExpand"
              :item-key-prop="itemKey"
              click-row-prop
              show-pagination-prop
              @itemExpanded="expand"
              @clickRow="handlerClickExpanded"
              :custom-sort-prop="customSort"
          />

        </ExpandBox>

      </v-flex>
    </v-layout>
  </v-container>

</template>

<script>
import {VContainer, VLayout, VFlex, VCard} from "vuetify/lib/components";
import numerUtils from "@/js/utils/numer-utils";
import BaseDataTable from "@/components/base/BaseDataTable.vue";
import BaseExpandedDataTable from "@/components/base/BaseExpandedDataTable.vue";
import BaseTooltip from "@/components/base/BaseTooltip.vue";
import ExpandBox from "@/components/layout/ExpandBox.vue";
import Setting from "@/components/setting/Setting";

export default {
  name: "BaseComponents",
  components: {
    ExpandBox, Setting,
    "v-container": VContainer,
    "v-layout": VLayout,
    "v-flex": VFlex,
    "v-card": VCard,
    BaseDataTable, BaseExpandedDataTable, BaseTooltip
  },
  computed: {
    itemsLength() {
      if (this.items === null) {
        return 0;
      }
      return this.items.length;
    },
  },
  data() {
    return {
      items: [
        {
          number: 1,
          name: "Сбербанк России <base-tooltip />",
          industry: "<span class='table__holdings__square'>Банки</span>",
          proceeds2022: "992",
          proceeds2021: "13 555 455",
          proceeds2020: 130,
          profit2022: 145,
          profit2021: 45,
          profit2020: 321
        },
        {
          number: 2,
          name: "ВТБ",
          industry: "Банки",
          proceeds2022: "990 129",
          proceeds2021: "4 585 860",
          proceeds2020: 130,
          profit2022: 145,
          profit2021: 43,
          profit2020: 376
        },
        {
          number: 3,
          name: "Тинькофф <base-tooltip />",
          industry: "Банки",
          proceeds2022: "99 270",
          proceeds2021: "2 691 461",
          proceeds2020: 130,
          profit2022: 145,
          profit2021: 41,
          profit2020: 307
        },
        {
          number: 4,
          name: "МТС Банк <base-tooltip />",
          industry: "Банки",
          proceeds2022: "900",
          proceeds2021: "1 818 926",
          proceeds2020: 130,
          profit2022: 145,
          profit2021: 49,
          profit2020: 401
        },
        {
          number: 5,
          name: "Альфа-банк <base-tooltip />",
          industry: "Банки",
          proceeds2022: "91 120",
          proceeds2021: "1 600 645",
          proceeds2020: 130,
          profit2022: 145,
          profit2021: 50,
          profit2020: 354
        },
        {
          number: 6,
          name: "Уралсиб <base-tooltip />",
          industry: "Банки",
          proceeds2022: "930",
          proceeds2021: null,
          proceeds2020: 130,
          profit2022: 145,
          profit2021: 51,
          profit2020: 378
        },
        {
          number: 7,
          name: "Газпром-банк <base-tooltip />",
          industry: "Банки",
          proceeds2022: "9",
          proceeds2021: "855 816",
          proceeds2020: 133,
          profit2022: 147,
          profit2021: 76,
          profit2020: 335
        },
        {
          number: 8,
          name: "Сбербанк России <base-tooltip />",
          industry: "Банки",
          proceeds2022: "34 777",
          proceeds2021: "685 207",
          proceeds2020: 133,
          profit2022: 147,
          profit2021: 76,
          profit2020: 335
        },
        {
          number: 9,
          name: "Сбербанк России <base-tooltip />",
          industry: "Банки",
          proceeds2022: null,
          proceeds2021: null,
          proceeds2020: 133,
          profit2022: 147,
          profit2021: 76,
          profit2020: 335
        },
        {
          number: 10,
          name: "Сбербанк России <base-tooltip />",
          industry: "Банки",
          proceeds2022: "15 875",
          proceeds2021: "476 970",
          proceeds2020: 133,
          profit2022: 147,
          profit2021: 76,
          profit2020: 335
        },
        {
          number: 11,
          name: "Сбербанк России <base-tooltip />",
          industry: "Банки",
          proceeds2022: null,
          proceeds2021: "456",
          proceeds2020: 133,
          profit2022: 147,
          profit2021: 76,
          profit2020: 335
        },
        {
          number: 12,
          name: "Сбербанк России <base-tooltip />",
          industry: "Банки",
          proceeds2022: "1 220",
          proceeds2021: "76",
          proceeds2020: 133,
          profit2022: 147,
          profit2021: 76,
          profit2020: 335
        }
      ],
      headersOneLevel: [
        {
          value: "number",
          text: "№",
          sortable: true,
          filterable: true,
          hidden: false
        },
        {
          value: "name",
          text: "Название ГК/Компания",
          sortable: true,
          filterable: true,
          hidden: false,
          align: "end"
        },
        {
          value: "industry",
          text: "Отрасль",
          sortable: true,
          filterable: true,
          hidden: false
        },
        {
          value: "proceeds2022",
          text: "<span>2022</span>",
          sortable: true,
          filterable: true,
          hidden: false,
          width: "10%"
        },
        {
          value: "proceeds2021",
          text: "2021",
          sortable: true,
          filterable: true,
          hidden: false,
          width: "10%"
        },
        {
          value: "proceeds2020",
          text: "2020",
          sortable: true,
          filterable: true,
          hidden: false,
          width: "10%"
        },
        {
          value: "profit2022",
          text: "2022",
          sortable: true,
          filterable: true,
          hidden: false,
          width: "10%"
        },
        {
          value: "profit2021",
          text: "2021",
          sortable: true,
          filterable: true,
          hidden: false,
          width: "10%"
        },
        {
          value: "profit2020",
          text: "2020",
          sortable: true,
          filterable: true,
          hidden: false,
          width: "10%"
        }
      ],
      headers: [
        {
          value: "main",
          text: "",
          subHeaders: [
            {
              value: "number",
              text: "№",
              sortable: true,
              filterable: true,
              hidden: false
            },
            {
              value: "name",
              text: "Название ГК/Компания",
              sortable: true,
              filterable: true,
              hidden: false,
              align: "end"
            },
            {
              value: "industry",
              text: "Отрасль",
              sortable: true,
              filterable: true,
              hidden: false
            }
          ]
        },
        {
          value: "proceeds",
          text: "Выручка ГК",
          align: "end",
          subHeaders: [
            {
              value: "proceeds2022",
              text: "<span>2022</span>",
              sortable: true,
              filterable: true,
              hidden: false,
              width: "10%"
            },
            {
              value: "proceeds2021",
              text: "2021",
              sortable: true,
              filterable: true,
              hidden: false,
              width: "10%"
            },
            {
              value: "proceeds2020",
              text: "2020",
              sortable: true,
              filterable: true,
              hidden: false,
              width: "10%"
            }
          ]
        },
        {
          value: "profit",
          text: "Выручка РТК",
          subHeaders: [
            {
              value: "profit2022",
              text: "2022",
              sortable: true,
              filterable: true,
              hidden: false,
              width: "10%"
            },
            {
              value: "profit2021",
              text: "2021",
              sortable: true,
              filterable: true,
              hidden: false,
              width: "10%"
            },
            {
              value: "profit2020",
              text: "2020",
              sortable: true,
              filterable: true,
              hidden: false,
              width: "10%"
            }
          ]
        }
      ],
      expanded: {},
      options: {
        sortBy: ["number"],
        sortDesc: [true]
      },
      showSetting: false,
      showSettingExpand: false,
      searchText: "",
      searchTextExpand: "",
      itemKey: "number",
    }
  },
  methods: {
    ...numerUtils,
    expand(data) {
      // Добавить получение данных по внешнему API
      let headers = [
        {
          value: "main",
          text: "",
          subHeaders: [
            {value: "empty", text: "", width: "80px"},
            {value: "dzo", text: "ДЗО", sortable: true}
          ]
        },
        {
          value: "virAll",
          text: "Выручка, млн. Р.",
          subHeaders: [
            {value: "year2020", text: "2020", sortable: true, width: "10%"},
            {value: "year2021", text: "2021", sortable: true, width: "10%"},
            {value: "year2022", text: "2022", sortable: true, width: "10%"}
          ]
        },
        {
          value: "plan",
          text: "План, млн. Р.",
          subHeaders: [
            {value: "year2023", text: "2023", sortable: true, width: "30%"}
          ]
        }
      ];

      let items = [
        {empty: "", dzo: "ПАО РТК", year2020: "13.00", year2021: "13,00", year2022: "13,00", year2023: "13,00"},
        {empty: "", dzo: "РТК ЦОД", year2020: "12.00", year2021: "12,00", year2022: "13,00", year2023: "13,00"},
        {empty: "", dzo: "РТК Солар", year2020: "11.00", year2021: "11,00", year2022: "13,00", year2023: "13,00"},
        {empty: "", dzo: "Теле2", year2020: "10.00", year2021: "10,00", year2022: "13,00", year2023: "13,00"},
        {empty: "", dzo: "ПАО РТК1", year2020: "13.00", year2021: "13,00", year2022: "13,00", year2023: "13,00"},
        {empty: "", dzo: "РТК ЦОД1", year2020: "12.00", year2021: "12,00", year2022: "13,00", year2023: "13,00"},
        {empty: "", dzo: "РТК Солар1", year2020: "11.00", year2021: "11,00", year2022: "13,00", year2023: "13,00"},
        {empty: "", dzo: "Теле21", year2020: "10.00", year2021: "10,00", year2022: "13,00", year2023: "13,00"},
        {empty: "", dzo: "ПАО РТК2", year2020: "13.00", year2021: "13,00", year2022: "13,00", year2023: "13,00"},
        {empty: "", dzo: "РТК ЦОД2", year2020: "12.00", year2021: "12,00", year2022: "13,00", year2023: "13,00"},
        {empty: "", dzo: "РТК Солар2", year2020: "11.00", year2021: "11,00", year2022: "13,00", year2023: "13,00"},
        {empty: "", dzo: "Теле22", year2020: "10.00", year2021: "10,00", year2022: "13,00", year2023: "13,00"}
      ];

      this.expanded[data[this.itemKey]] = {headers: headers, items: items};
    }

    // можно задать кастомную фукнцию сортировки
    // this.expanded.customSort = (items, sortBy, sortDesc) => {
    // return items;
    //}
  },
  // handlerClick(data) {
  //   // console.log("!!! click !!!", data);
  // },
  // handlerClickExpanded(data) {
  //   // console.log("!!! clickExpanded !!!", data);
  // },
  onShowSetting() {
    this.showSetting = !this.showSetting;
  },
  onShowSettingExpand() {
    this.showSettingExpand = !this.showSettingExpand;
  },
  onSearchName(val) {
    this.searchText = val;
  },
  onSearchNameExpand(val) {
    this.searchTextExpand = val;
  },
  customSort(items, sortBy, sortDesc) {
    const index = sortBy[0];
    return items.sort((x, y) => {
      if (["proceeds2022", "proceeds2021"].includes(index)) {
        x = x[index] ? this.numberWithoutSpaces(x[index]) : x[index];
        y = y[index] ? this.numberWithoutSpaces(y[index]) : y[index];

        if (!sortDesc[0]) {
          return x < y ? -1 : 1;
        } else {
          return y < x ? -1 : 1;
        }
      } else {
        if (!sortDesc[0]) {
          return x[index] < y[index] ? -1 : 1;
        } else {
          return y[index] < x[index] ? -1 : 1;
        }
      }
    })
  },
  calcWidth() {
    return 10;
  }
}

</script>

<style scoped lang="less">
@import "./css/colors";

main {
  height: 100vh;
  // background-color: @gray5;

  h2 {
    font-size: 32px;
    font-weight: 700;
  }
}

.table__holdings__square {
  align-items: center;
  padding: 4px 8px;
  background: rgba(31, 105, 255, 0.12);
  border-radius: 3px;
  width: fit-content;
}

.red {
  color: red !important;
}
</style>