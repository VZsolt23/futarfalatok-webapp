import { Outlet } from "react-router-dom";
import { useLocation } from 'react-router-dom';
import Header from "./header/Header";
import Footer from "./footer/Footer";

import React from "react";

const Layout = () => {
    const location = useLocation();

    return(
        <main>
            <Header />
            <Outlet />
            <Footer />
        </main>
    )
}

export default Layout