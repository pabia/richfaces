/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.richfaces.ui.placeholder;

import java.net.URL;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.warp.impl.utils.URLUtils;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.richfaces.deployment.FrameworkDeployment;
import org.richfaces.shrinkwrap.descriptor.FaceletAsset;

import category.Smoke;

/**
 * @author <a href="mailto:jstefek@redhat.com">Jiri Stefek</a>
 */
public class ITPlaceholderInputText extends AbstractPlaceholderTest {

    @FindBy(id = INPUT_ID)
    private Input firstInput;

    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        FrameworkDeployment deployment = new FrameworkDeployment(ITPlaceholderInputText.class);

        deployment.archive().addClasses(PlaceHolderValueConverter.class, PlaceHolderValue.class);

        FaceletAsset p;
        p = placeholderFacelet("index.xhtml", deployment);
        p.body("<h:panelGroup id='panel'>");
        p.body("<h:inputText id='input' >");
        p.body("    <r:placeholder id='placeholderID' styleClass='#{param.styleClass}' value='Placeholder Text' />");
        p.body("</h:inputText>");
        p.body("</h:panelGroup>");

        p = placeholderFacelet("selector.xhtml", deployment);
        p.body("<h:panelGroup id='panel'>");
        p.body("<h:inputText id='input' />");
        p.body("<r:placeholder id='placeholderID' value='Placeholder Text' selector='[id=input]' />");
        p.body("</h:panelGroup>");

        p = placeholderFacelet("rendered.xhtml", deployment);
        p.body("<h:inputText id='input' >");
        p.body("    <r:placeholder id='placeholderID' value='Placeholder Text' rendered='false' />");
        p.body("</h:inputText>");

        p = placeholderFacelet("converter.xhtml", deployment);
        p.body("<h:inputText id='input' >");
        p.body("    <r:placeholder id='placeholderID' converter='placeHolderValueConverter' value='#{placeHolderValue}' />");
        p.body("</h:inputText>");

        p = placeholderFacelet("submit.xhtml", deployment);
        p.form("<h:inputText id='input' value='#{placeHolderValue.value2}' >");
        p.form("    <r:placeholder id='placeholderID' value='Placeholder Text' />");
        p.form("</h:inputText>");
        p.form("<br />");
        p.form("<r:commandButton id='ajaxSubmit' value='ajax submit' execute='@form' render='output' />");
        p.form("<h:commandButton id='httpSubmit' value='http submit' />");
        p.form("<br />");
        p.form("<h:outputText id='output' value='#{placeHolderValue.value2}' />");

        return deployment.getFinalArchive();
    }

    @Override
    Input input() {
        return firstInput;
    }

    @Test
    @Category(Smoke.class)
    public void testComponentSourceWithoutSelector() throws Exception {
        URL urL = new URL(contextPath.toExternalForm() + "index.jsf");
        sourceChecker.checkComponentSource(urL, "placeholder-without-selector.xmlunit.xml", By.id("panel"));
    }

    @Test
    @Category(Smoke.class)
    public void testComponentSourceWithSelector() throws Exception {
        URL selectorUrl = URLUtils.buildUrl(contextPath, "selector.jsf?selector=input");
        sourceChecker.checkComponentSource(selectorUrl, "placeholder-with-selector.xmlunit.xml", By.id("panel"));
    }
}
