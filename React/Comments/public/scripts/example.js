
// Code for building the comment box
var CommentBox = React.createClass({
  render: function() { // Only function in the 'CommentBox' class
    return (
      <div className="commentBox">
        Hello, world! I am a CommentBox.
      </div>
    );
  }
});

// Instantiates the root component, starts the framework, 
// and injects the markup into a raw DOM element, provided as the second argument.
//
// It is important that ReactDOM.render remain at the bottom of the script for
// this tutorial. ReactDOM.render should only be called after the composite components
// have been defined.
ReactDOM.render(
  <CommentBox />,
  document.getElementById('content')
);