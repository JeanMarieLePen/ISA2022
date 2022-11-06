import Home from './main-components/Home'
import LoginForm from './forms/LoginForm'
import RegistrationForm from './forms/RegistrationForm'
import Homepage from './main-components/Homepage'

import SearchPage from './main-components/SearchPage'
export default[
    {
        path:`/`,
        name:`Home`,
        component:Home
    },
    {
        path:`/login`,
        name:'LoginForm',
        component:LoginForm
    },
    {
        path:`/register`,
        name:'RegistrationForm',
        component:RegistrationForm
    },
    {
        path:`/home`,
        name:'Homepage',
        component:Homepage
    },
    {
        path:`/search`,
        name:'SearchPage',
        component:SearchPage
    }
]
   