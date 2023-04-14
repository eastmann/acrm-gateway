<!--https://vuejsexamples.com/a-beautiful-sidebar-menu-built-with-vue-js/-->
<!--https://github.com/akahon/vue-sidebar-menu-akahon?ref=vuejsexamples.com-->
<template>
    <div class="sidebar" :class="isOpened ? 'open' : ''" :style="cssVars">
        <p class="rt-caption">
            <img class="menu-logo-main" src="../../assets/img/logo/rtk_logo.png">
            <span v-if="isOpened" class="rt-text">Ростелеком</span>
        </p>

        <!--        <div class="menu-logo-main">-->
        <!--            <img src="../../assets/img/logo/rtk_logo.png" alt="menu-logo">-->
        <!--                Ростелеком-->
        <!--        </div>-->

        <div style="display: flex ; flex-direction:column; justify-content: space-between; flex-grow: 1; max-height: calc(100% - 60px); ">
            <div id="my-scroll" style="overflow-x: hidden; margin: 6px 14px 0 14px;">
                <ul class="nav-list" style="overflow: visible; ">
                    <li v-if="isSearch" @click="isOpened = true">
                        <i class="bx bx-search"/>
                        <input
                                type="text"
                                :placeholder="searchPlaceholder"
                                @input="$emit('search-input-emit', $event.target.value)"
                        >
                        <span class="tooltip">{{ searchTooltip }}</span>
                    </li>

                    <span v-for="(menuItem, index) in menuItems" :key="index">
                        <li>
                            <a :href="menuItem.link">
                            <i class="bx" :class="menuItem.icon || 'bx-square-rounded'"/>
                            <span class="links_name">{{ menuItem.name }}</span></a>
                            <span class="tooltip">{{ menuItem.tooltip || menuItem.name }}</span>
                        </li>
                    </span>
                </ul>
            </div>

            <!--            Свернуть/Развернуть-->
            <div class="logo-details" style="margin: 6px 14px 0 14px;">
                <!--                <img v-if="menuLogo" :src="menuLogo" alt="menu-logo" class="menu-logo icon">-->
                <!--                <i v-else class="bx icon" :class="menuIcon"/>-->
                <!--                <div class="logo_name">-->
                <!--                    {{ menuTitle }}-->
                <!--                </div>-->

                <i v-if="isOpened" v-on:click="isOpened = !isOpened" class='bx bx-left-arrow-alt cur-pointer'></i>
                <span v-if="isOpened" v-on:click="isOpened = !isOpened" class="rt-hide">Свернуть</span>

                <i class="bx" :class="isOpened ? 'bx-menu-alt-right' : 'bx-menu'" id="btn"
                   @click="isOpened = !isOpened"/>
            </div>

        </div>
    </div>
</template>

<script>
    export default {
        name: 'SidebarMenuAkahon',
        props: {
            //! Menu settings
            isMenuOpen: {
                type: Boolean,
                default: true,
            },
            menuTitle: {
                type: String,
                default: 'Ростелеком',
            },
            menuLogo: {
                type: String,
                default: '',
            },
            menuIcon: {
                type: String,
                default: 'bxl-c-plus-plus',
            },
            isPaddingLeft: {
                type: Boolean,
                default: true,
            },
            menuOpenedPaddingLeftBody: {
                type: String,
                default: '250px'
            },
            menuClosedPaddingLeftBody: {
                type: String,
                default: '78px'
            },

            //! Menu items
            menuItems: {
                type: Array,
                default: () => [
                    {
                        link: '/holdings',
                        name: 'Список холдингов',
                        tooltip: 'Holdings',
                        icon: 'bx-file',
                    },
                    // {
                    //     link: '#',
                    //     name: 'Пользователи',
                    //     tooltip: 'User',
                    //     icon: 'bx-user',
                    // },
                    // {
                    //     link: '#',
                    //     name: 'Сообщения',
                    //     tooltip: 'Messages',
                    //     icon: 'bx-chat',
                    // },
                    // {
                    //     link: '#',
                    //     name: 'Аналитика',
                    //     tooltip: 'Analytics',
                    //     icon: 'bx-pie-chart-alt-2',
                    // },
                    // {
                    //     link: '#',
                    //     name: 'Файлы',
                    //     tooltip: 'Files',
                    //     icon: 'bx-folder',
                    // },
                    // {
                    //     link: '#',
                    //     name: 'Заказы',
                    //     tooltip: 'Order',
                    //     icon: 'bx-cart-alt',
                    // },
                    // {
                    //     link: '/save',
                    //     name: 'Избранное',
                    //     tooltip: 'Saved',
                    //     icon: 'bx-heart',
                    // },
                    // {
                    //     link: '/setting',
                    //     name: 'Настройка',
                    //     tooltip: 'Setting',
                    //     icon: 'bx-cog',
                    // },
                ],
            },

            //! Search
            isSearch: {
                type: Boolean,
                default: true,
            },
            searchPlaceholder: {
                type: String,
                default: 'Search...',
            },
            searchTooltip: {
                type: String,
                default: 'Search',
            },

            //! Styles
            bgColor: {
                type: String,
                default: '#F0EFFF',
            },
            secondaryColor: {
                type: String,
                default: '#1d1b31',
            },
            homeSectionColor: {
                type: String,
                default: '#e4e9f7',
            },
            logoTitleColor: {
                type: String,
                default: '#fff',
            },
            iconsColor: {
                type: String,
                default: '#9466FF',
            },
            itemsTooltipColor: {
                type: String,
                default: '#e4e9f7',
            },
            searchInputTextColor: {
                type: String,
                default: '#fff',
            },
            menuItemsHoverColor: {
                type: String,
                default: '#fff',
            },
            menuItemsTextColor: {
                type: String,
                default: '#101828',
            },
        },
        data() {
            return {
                isOpened: false,
            }
        },

        mounted() {
            this.isOpened = this.isMenuOpen
            window.document.body.style.paddingLeft = this.isOpened && this.isPaddingLeft ? this.menuOpenedPaddingLeftBody : this.menuClosedPaddingLeftBody
        },

        computed: {
            cssVars() {
                return {
                    '--padding-left-body': this.isOpened ? this.menuOpenedPaddingLeftBody : this.menuClosedPaddingLeftBody,
                    '--bg-color': this.bgColor,
                    '--secondary-color': this.secondaryColor,
                    '--home-section-color': this.homeSectionColor,
                    '--logo-title-color': this.logoTitleColor,
                    '--icons-color': this.iconsColor,
                    '--items-tooltip-color': this.itemsTooltipColor,
                    '--serach-input-text-color': this.searchInputTextColor,
                    '--menu-items-hover-color': this.menuItemsHoverColor,
                    '--menu-items-text-color': this.menuItemsTextColor,
                }
            },
        },
        watch: {
            isOpened() {
                this.setMenuOpenInStrogare(this.isOpened);
                window.document.body.style.paddingLeft = this.isOpened && this.isPaddingLeft ? this.menuOpenedPaddingLeftBody : this.menuClosedPaddingLeftBody
            }
        },

        methods: {
            setMenuOpenInStrogare(val) {
                localStorage.isMenuOpen = val;
            },
        }
    }
</script>

<style>
    @import url('https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css');

    * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
        font-family: 'Poppins', sans-serif;
    }

    body {
        transition: all 0.5s ease;
    }

    .menu-logo {
        width: 30px;
        margin: 0 10px 0 10px;
    }

    .menu-logo-main {
        /*margin: 30px 30px 0 10px;*/
        width: 25px;
    }

    .sidebar {
        display: flex;
        flex-direction: column;
        position: fixed;
        left: 0;
        top: 0;
        height: 100%;
        min-height: min-content;
        /* overflow-y: auto; */
        width: 78px;
        background: var(--bg-color);
        /* padding: 6px 14px 0 14px; */
        z-index: 99;
        transition: all 0.5s ease;
    }

    .sidebar.open {
        width: 250px;
    }

    .sidebar .logo-details {
        height: 60px;
        display: flex;
        align-items: center;
        position: relative;
    }

    .sidebar .logo-details .icon {
        opacity: 0;
        transition: all 0.5s ease;
    }

    .sidebar .logo-details .logo_name {
        color: var(--logo-title-color);
        font-size: 20px;
        font-weight: 600;
        opacity: 0;
        transition: all 0.5s ease;
    }

    .sidebar.open .logo-details .icon,
    .sidebar.open .logo-details .logo_name {
        opacity: 1;
    }

    .sidebar .logo-details #btn {
        position: absolute;
        top: 50%;
        right: 0;
        transform: translateY(-50%);
        font-size: 23px;
        text-align: center;
        cursor: pointer;
        transition: all 0.5s ease;
    }

    .sidebar.open .logo-details #btn {
        text-align: right;
    }

    .sidebar i {
        color: var(--icons-color);
        height: 60px;
        min-width: 50px;
        font-size: 28px;
        text-align: center;
        line-height: 60px;
    }

    .sidebar .nav-list {
        margin-top: 20px;
        /* margin-bottom: 60px; */
        height: 50px;

        /* min-height: min-content; */
    }

    .sidebar li {
        position: relative;
        margin: 8px 0;
        list-style: none;
    }

    .sidebar li .tooltip {
        position: absolute;
        top: -20px;
        left: calc(100% + 15px);
        z-index: 3;
        background: var(--items-tooltip-color);
        box-shadow: 0 5px 10px rgba(0, 0, 0, 0.3);
        padding: 6px 12px;
        border-radius: 4px;
        font-size: 15px;
        font-weight: 400;
        opacity: 0;
        white-space: nowrap;
        pointer-events: none;
        transition: 0s;
    }

    .sidebar li:hover .tooltip {
        opacity: 1;
        pointer-events: auto;
        transition: all 0.4s ease;
        top: 50%;
        transform: translateY(-50%);
    }

    .sidebar.open li .tooltip {
        display: none;
    }

    .sidebar input {
        font-size: 15px;
        color: var(--serach-input-text-color);
        font-weight: 400;
        outline: none;
        height: 50px;
        width: 50px;
        border: none;
        border-radius: 12px;
        transition: all 0.5s ease;
        background: var(--secondary-color);
    }

    .sidebar.open input {
        padding: 0 20px 0 50px;
        width: 100%;
    }

    .sidebar .bx-search {
        position: absolute;
        top: 50%;
        left: 0;
        transform: translateY(-50%);
        font-size: 22px;
        background: var(--secondary-color);
        color: var(--icons-color);
    }

    .sidebar.open .bx-search:hover {
        background: var(--secondary-color);
        color: var(--icons-color);
    }

    .sidebar .bx-search:hover {
        background: var(--menu-items-hover-color);
        color: var(--bg-color);
    }

    .sidebar li a {
        display: flex;
        height: 100%;
        width: 100%;
        border-radius: 12px;
        align-items: center;
        text-decoration: none;
        transition: all 0.4s ease;
        background: var(--bg-color);
    }

    .sidebar li a:hover {
        background: var(--menu-items-hover-color);
    }

    .sidebar li a .links_name {
        color: var(--menu-items-text-color);
        font-size: 15px;
        font-weight: 400;
        white-space: nowrap;
        opacity: 0;
        pointer-events: none;
        transition: 0.4s;
    }

    .sidebar.open li a .links_name {
        opacity: 1;
        pointer-events: auto;
    }

    .sidebar li a:hover .links_name,
    .sidebar li a:hover i {
        transition: all 0.5s ease;
        color: var(--bg-color);
    }

    .sidebar li i {
        height: 50px;
        line-height: 50px;
        font-size: 18px;
        border-radius: 12px;
    }

    .sidebar div img {
        height: 45px;
        width: 45px;
        object-fit: cover;
        border-radius: 6px;
        margin-right: 10px;
    }

    .home-section {
        position: relative;
        background: var(--home-section-color);
        min-height: 100vh;
        top: 0;
        left: 78px;
        width: calc(100% - 78px);
        transition: all 0.5s ease;
        z-index: 2;
    }

    .sidebar.open ~ .home-section {
        left: 250px;
        width: calc(100% - 250px);
    }

    .home-section .text {
        display: inline-block;
        color: var(--bg-color);
        font-size: 25px;
        font-weight: 500;
        margin: 18px;
    }

    .my-scroll-active {
        overflow-y: auto;
    }

    #my-scroll {
        overflow-y: auto;
        height: calc(100% - 60px);
    }

    #my-scroll::-webkit-scrollbar {
        display: none;
        /* background-color: rgba(255, 255, 255, 0.2);
        width: 10px;
        border-radius:5px  */
    }

    /* #my-scroll::-webkit-scrollbar-thumb{
      background-color: red;
      border-radius:5px
    }
    #my-scroll::-webkit-scrollbar-button:vertical:start:decrement{
      display:none;
    }
    #my-scroll::-webkit-scrollbar-button:vertical:end:increment{
      display:none;
    } */
    @media (max-width: 420px) {
        .sidebar li .tooltip {
            display: none;
        }
    }

    .rt-caption {
        display: flex;
        align-items: center;
        margin-top: 30px;
        margin-left: 30px;
        /*width: 25px;*/
    }

    .rt-text {
        margin-left: 10px;
        font-weight: bold;
        font-size: 18px;
    }

    .rt-hide {
        font-size: 16px;
        color: #9466FF;
        cursor: pointer;
    }

    .cur-pointer {
        cursor: pointer;
    }
</style>