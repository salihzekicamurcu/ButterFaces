package de.larmic.butterfaces.component.html;

import de.larmic.butterfaces.component.html.feature.AutoFocus;
import de.larmic.butterfaces.component.html.feature.Placeholder;

import javax.el.ValueExpression;
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.html.HtmlInputText;
import java.util.Collections;
import java.util.List;

@ResourceDependencies({
		@ResourceDependency(library = "butterfaces-css", name = "butterfaces-default.css", target = "head"),
		@ResourceDependency(library = "butterfaces-js", name = "butterfaces-default.js", target = "head"),
        @ResourceDependency(library = "butterfaces-configurable", name = "jquery.min.js", target = "head"),
        @ResourceDependency(library = "butterfaces-configurable", name = "bootstrap.min.css", target = "head"),
        @ResourceDependency(library = "butterfaces-configurable", name = "bootstrap.min.js", target = "head"),
        @ResourceDependency(library = "butterfaces-external", name = "bootstrap-tagsinput.css", target = "head"),
        @ResourceDependency(library = "butterfaces-external", name = "bootstrap-tagsinput.js", target = "head"),
		@ResourceDependency(library = "butterfaces-css", name = "butterfaces-tags.css", target = "head"),
        @ResourceDependency(library = "butterfaces-js", name = "butterfaces-tooltip.jquery.js", target = "head")
})
@FacesComponent(HtmlTags.COMPONENT_TYPE)
public class HtmlTags extends HtmlInputText implements HtmlInputComponent, AutoFocus, Placeholder {

	public static final String COMPONENT_TYPE = "de.larmic.butterfaces.component.tags";
	public static final String COMPONENT_FAMILY = "de.larmic.butterfaces.component.family";
	public static final String RENDERER_TYPE = "de.larmic.butterfaces.component.renderkit.html_basic.TagsRenderer";

	protected static final String PROPERTY_TOOLTIP = "tooltip";
    protected static final String PROPERTY_HIDE_LABEL = "hideLabel";
    protected static final String PROPERTY_INPUT_STYLE_CLASS = "inputStyleClass";
	protected static final String PROPERTY_LABEL_STYLE_CLASS = "labelStyleClass";
	protected static final String PROPERTY_HTML5_PLACEHOLDER = "placeholder";
	protected static final String PROPERTY_HTML5_AUTO_FOCUS = "autoFocus";

	protected static final String PROPERTY_MAX_TAGS = "maxTags";
	protected static final String PROPERTY_MAX_CHARS = "maxChars";
	protected static final String PROPERTY_TRIM_VALUE = "trimValue";
	protected static final String PROPERTY_ALLOW_DUPLICATES = "allowDuplicates";

	public HtmlTags() {
		super();
		this.setRendererType(RENDERER_TYPE);
	}

	@Override
	public List<InputComponentFacet> getSupportedFacets() {
		return Collections.emptyList();
	}

	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	@Override
	public String getTooltip() {
		return (String) this.getStateHelper().eval(PROPERTY_TOOLTIP);
	}

	public void setTooltip(final String tooltip) {
		this.updateStateHelper(PROPERTY_TOOLTIP, tooltip);
	}

    @Override
    public boolean isHideLabel() {
		final Object eval = this.getStateHelper().eval(PROPERTY_HIDE_LABEL);
		return eval == null ? false : (Boolean) eval;
    }

    public void setHideLabel(final boolean hideLabel) {
        this.updateStateHelper(PROPERTY_HIDE_LABEL, hideLabel);
    }

	@Override
	public String getPlaceholder() {
		return (String) this.getStateHelper().eval(PROPERTY_HTML5_PLACEHOLDER);
	}

	@Override
	public void setPlaceholder(final String placeholder) {
		this.updateStateHelper(PROPERTY_HTML5_PLACEHOLDER, placeholder);
	}

	@Override
    public boolean getAutoFocus() {
		final Object eval = this.getStateHelper().eval(PROPERTY_HTML5_AUTO_FOCUS);
		return eval == null ? false : (Boolean) eval;
    }

	@Override
    public void setAutoFocus(final boolean autoFocus) {
        this.updateStateHelper(PROPERTY_HTML5_AUTO_FOCUS, autoFocus);
    }

	@Override
    public String getInputStyleClass() {
        return (String) this.getStateHelper().eval(PROPERTY_INPUT_STYLE_CLASS);
    }

    public void setInputStyleClass(final String inputStyleClass) {
        this.updateStateHelper(PROPERTY_INPUT_STYLE_CLASS, inputStyleClass);
    }

    @Override
    public String getLabelStyleClass() {
        return (String) this.getStateHelper().eval(PROPERTY_LABEL_STYLE_CLASS);
    }

    public void setLabelStyleClass(final String labelStyleClass) {
        this.updateStateHelper(PROPERTY_LABEL_STYLE_CLASS, labelStyleClass);
    }

	public Integer getMaxTags() {
		return (Integer) this.getStateHelper().eval(PROPERTY_MAX_TAGS);
	}

	public void setMaxTags(Integer maxTags) {
		this.updateStateHelper(PROPERTY_MAX_TAGS, maxTags);
	}

	public Integer getMaxChars() {
		return (Integer) this.getStateHelper().eval(PROPERTY_MAX_CHARS);
	}

	public void setMaxChars(Integer maxChars) {
		this.updateStateHelper(PROPERTY_MAX_CHARS, maxChars);
	}

	public boolean isTrimValue() {
		final Object eval = this.getStateHelper().eval(PROPERTY_TRIM_VALUE);
		return eval == null ? false : (Boolean) eval;
	}

	public void setTrimValue(boolean trimValue) {
		this.updateStateHelper(PROPERTY_TRIM_VALUE, trimValue);
	}

	public boolean isAllowDuplicates() {
		final Object eval = this.getStateHelper().eval(PROPERTY_ALLOW_DUPLICATES);
		return eval == null ? false : (Boolean) eval;
	}

	public void setAllowDuplicates(boolean allowDuplicates) {
		this.updateStateHelper(PROPERTY_ALLOW_DUPLICATES, allowDuplicates);
	}

	private void updateStateHelper(final String propertyName, final Object value) {
		this.getStateHelper().put(propertyName, value);

		final ValueExpression ve = this.getValueExpression(propertyName);

		if (ve != null) {
			ve.setValue(this.getFacesContext().getELContext(), value);
		}
	}
}
