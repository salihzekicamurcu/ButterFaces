package de.larmic.butterfaces.component.renderkit.html_basic.text.part;

import de.larmic.butterfaces.component.partrenderer.StringUtils;
import de.larmic.butterfaces.component.renderkit.html_basic.reflect.ReflectionUtil;
import de.larmic.butterfaces.model.tree.Node;

import javax.faces.component.html.HtmlInputText;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TrivialComponentsEntriesNodePartRenderer {

    /**
     * Renders a list of entities required by trivial components tree.
     *
     * @param nodes        a list of nodes that should be rendered
     * @param index        index of corresponding node (should match cachedNodes
     * @param mustacheKeys optional mustache keys
     * @param cachedNodes  a map of node number to node
     * @return the last rendered index + 1
     */
    public int renderNodes(final StringBuilder stringBuilder,
                           final List<Node> nodes,
                           final int index,
                           final List<String> mustacheKeys,
                           final Map<Integer, Node> cachedNodes) {
        int newIndex = index;

        final Iterator<Node> iterator = nodes.iterator();

        while (iterator.hasNext()) {
            final Node node = iterator.next();
            newIndex = this.renderNode(stringBuilder, mustacheKeys, cachedNodes, newIndex, node, true);

            if (iterator.hasNext()) {
                stringBuilder.append(",");
            }
        }

        return newIndex;
    }

    public String renderNode(final List<String> mustacheKeys,
                             final Map<Integer, Node> cachedNodes,
                             final int index,
                             final Node node) {
        final StringBuilder renderString = new StringBuilder();

        this.renderNode(renderString, mustacheKeys, cachedNodes, index, node, false);

        return renderString.toString();
    }

    public static String getEditingMode(final HtmlInputText text) {
        if (text.isReadonly()) {
            return "readonly";
        } else if (text.isDisabled()) {
            return "disabled";
        }

        return "editable";
    }

    private int renderNode(final StringBuilder stringBuilder,
                          final List<String> mustacheKeys,
                          final Map<Integer, Node> cachedNodes,
                          final int index,
                          final Node node,
                          final boolean renderChildren) {
        int newIndex = index;

        stringBuilder.append("{");
        stringBuilder.append("\"id\": " + newIndex + ",");
        if (StringUtils.isNotEmpty(node.getStyleClass())) {
            stringBuilder.append("\"styleClass\": \"" + node.getStyleClass() + "\",");
        }
        if (StringUtils.isNotEmpty(node.getImageIcon())) {
            stringBuilder.append("\"imageStyle\": \"background-image: url(" + node.getImageIcon() + ")\",");
        } else if (StringUtils.isNotEmpty(node.getGlyphiconIcon())) {
            stringBuilder.append("\"imageClass\": \"" + node.getGlyphiconIcon() + " glyphicon-node\",");
        } else {
            stringBuilder.append("\"imageStyle\": \"display:none\",");
        }

        if (StringUtils.isNotEmpty(node.getDescription())) {
            stringBuilder.append("\"description\": \"" + node.getDescription() + "\",");
        }

        for (String mustacheKey : mustacheKeys) {
            stringBuilder.append("\"" + mustacheKey + "\": \"" + new ReflectionUtil().getValueFromObject(node.getData(), mustacheKey) + "\",");
        }

        stringBuilder.append("\"expanded\": " + Boolean.toString(!cachedNodes.get(newIndex).isCollapsed()) + ",");
        stringBuilder.append("\"title\": \"" + node.getTitle() + "\"");

        newIndex++;

        if (node.getSubNodes().size() > 0 && renderChildren) {
            stringBuilder.append(",\"children\": [");
            newIndex = renderNodes(stringBuilder, node.getSubNodes(), newIndex, mustacheKeys, cachedNodes);
            stringBuilder.append("]");
        }

        stringBuilder.append("}");
        return newIndex;
    }
}